//! Just enough of the JVM class-file format to read what a method does with constants.
//!
//! A method's bytecode becomes a flat list of [`Op`]s: the string constants it loads, the
//! methods it calls, the static fields it reads and writes, and the classes it creates.
//! Everything else is skipped by length. That is all the channel extraction needs, and it
//! avoids simulating the operand stack.

/// What one instruction does, for the instructions that matter here.
#[derive(Debug, Clone, PartialEq, Eq)]
pub enum Op {
    Ldc(String),
    Invoke {
        owner: String,
        name: String,
        desc: String,
    },
    GetStatic {
        owner: String,
        name: String,
    },
    PutStatic {
        owner: String,
        name: String,
    },
    New(String),
    /// Any other instruction.
    Other,
}

/// One method: its name, descriptor and what its code does.
pub struct Method {
    pub name: String,
    pub desc: String,
    pub ops: Vec<Op>,
}

/// One class: its name and methods.
pub struct Class {
    pub name: String,
    pub methods: Vec<Method>,
}

enum Constant {
    Utf8(String),
    Class(u16),
    String(u16),
    Ref { class: u16, name_and_type: u16 },
    NameAndType { name: u16, desc: u16 },
    Other,
}

struct Reader<'a> {
    bytes: &'a [u8],
    pos: usize,
}

impl Reader<'_> {
    fn take(&mut self, n: usize) -> Option<&[u8]> {
        let slice = self.bytes.get(self.pos..self.pos + n)?;
        self.pos += n;
        Some(slice)
    }
    fn u8(&mut self) -> Option<u8> {
        Some(self.take(1)?[0])
    }
    fn u16(&mut self) -> Option<u16> {
        let b = self.take(2)?;
        Some(u16::from_be_bytes([b[0], b[1]]))
    }
    fn u32(&mut self) -> Option<u32> {
        let b = self.take(4)?;
        Some(u32::from_be_bytes([b[0], b[1], b[2], b[3]]))
    }
}

/// Parses a class file, or `None` if it is not one this reader understands.
pub fn parse(bytes: &[u8]) -> Option<Class> {
    let mut r = Reader { bytes, pos: 0 };
    if r.u32()? != 0xCAFE_BABE {
        return None;
    }
    r.take(4)?; // minor, major

    let pool = constant_pool(&mut r)?;

    let utf8 = |i: u16| match pool.get(i as usize) {
        Some(Constant::Utf8(s)) => Some(s.clone()),
        _ => None,
    };
    let class_name = |i: u16| match pool.get(i as usize) {
        Some(Constant::Class(name)) => utf8(*name),
        _ => None,
    };
    let member = |i: u16| -> Option<(String, String, String)> {
        let Some(Constant::Ref {
            class,
            name_and_type,
        }) = pool.get(i as usize)
        else {
            return None;
        };
        let Some(Constant::NameAndType { name, desc }) = pool.get(*name_and_type as usize) else {
            return None;
        };
        Some((class_name(*class)?, utf8(*name)?, utf8(*desc)?))
    };

    r.take(2)?; // access flags
    let name = class_name(r.u16()?)?;
    r.take(2)?; // super
    let interfaces = r.u16()? as usize;
    r.take(interfaces * 2)?;

    let fields = r.u16()?;
    for _ in 0..fields {
        r.take(6)?;
        skip_attributes(&mut r)?;
    }

    let mut methods = Vec::new();
    for _ in 0..r.u16()? {
        r.take(2)?;
        let name = utf8(r.u16()?)?;
        let desc = utf8(r.u16()?)?;
        let mut ops = Vec::new();
        for _ in 0..r.u16()? {
            let attr_name = utf8(r.u16()?)?;
            let len = r.u32()? as usize;
            let body = r.take(len)?;
            if attr_name == "Code" {
                let code_len = u32::from_be_bytes(body.get(4..8)?.try_into().ok()?) as usize;
                ops = decode(
                    body.get(8..8 + code_len)?,
                    &|i| match pool.get(i as usize) {
                        Some(Constant::String(s)) => utf8(*s).map(Op::Ldc),
                        _ => None,
                    },
                    &member,
                    &class_name,
                );
            }
        }
        methods.push(Method { name, desc, ops });
    }
    Some(Class { name, methods })
}

/// Reads the constant pool, index 0 included as a placeholder so indices line up.
fn constant_pool(r: &mut Reader) -> Option<Vec<Constant>> {
    let count = r.u16()?;
    let mut pool: Vec<Constant> = vec![Constant::Other];
    let mut index = 1;
    while index < count {
        let tag = r.u8()?;
        let constant = match tag {
            1 => {
                let len = r.u16()? as usize;
                // Modified UTF-8; lossy is fine for the identifiers read here.
                Constant::Utf8(String::from_utf8_lossy(r.take(len)?).into_owned())
            }
            7 => Constant::Class(r.u16()?),
            8 => Constant::String(r.u16()?),
            9..=11 => Constant::Ref {
                class: r.u16()?,
                name_and_type: r.u16()?,
            },
            12 => Constant::NameAndType {
                name: r.u16()?,
                desc: r.u16()?,
            },
            3 | 4 | 17 | 18 => {
                r.take(4)?;
                Constant::Other
            }
            5 | 6 => {
                r.take(8)?;
                // Longs and doubles take two slots.
                pool.push(Constant::Other);
                index += 1;
                Constant::Other
            }
            15 => {
                r.take(3)?;
                Constant::Other
            }
            16 | 19 | 20 => {
                r.take(2)?;
                Constant::Other
            }
            _ => return None,
        };
        pool.push(constant);
        index += 1;
    }

    Some(pool)
}

fn skip_attributes(r: &mut Reader) -> Option<()> {
    for _ in 0..r.u16()? {
        r.take(2)?;
        let len = r.u32()? as usize;
        r.take(len)?;
    }
    Some(())
}

fn decode(
    code: &[u8],
    ldc: &dyn Fn(u16) -> Option<Op>,
    member: &dyn Fn(u16) -> Option<(String, String, String)>,
    class_name: &dyn Fn(u16) -> Option<String>,
) -> Vec<Op> {
    let u16_at = |i: usize| code.get(i..i + 2).map(|b| u16::from_be_bytes([b[0], b[1]]));
    let i32_at = |i: usize| {
        code.get(i..i + 4)
            .map(|b| i32::from_be_bytes([b[0], b[1], b[2], b[3]]))
    };
    let mut ops = Vec::new();
    let mut pc = 0;
    while pc < code.len() {
        let opcode = code[pc];
        let (op, len) = match opcode {
            0x12 => (
                code.get(pc + 1)
                    .and_then(|&i| ldc(u16::from(i)))
                    .unwrap_or(Op::Other),
                2,
            ),
            0x13 => (u16_at(pc + 1).and_then(ldc).unwrap_or(Op::Other), 3),
            0xb2 | 0xb3 => {
                let op = u16_at(pc + 1)
                    .and_then(member)
                    .map_or(Op::Other, |(owner, name, _)| {
                        if opcode == 0xb2 {
                            Op::GetStatic { owner, name }
                        } else {
                            Op::PutStatic { owner, name }
                        }
                    });
                (op, 3)
            }
            0xb6..=0xb9 => {
                let op =
                    u16_at(pc + 1)
                        .and_then(member)
                        .map_or(Op::Other, |(owner, name, desc)| Op::Invoke {
                            owner,
                            name,
                            desc,
                        });
                (op, if opcode == 0xb9 { 5 } else { 3 })
            }
            0xbb => (
                u16_at(pc + 1)
                    .and_then(class_name)
                    .map_or(Op::Other, Op::New),
                3,
            ),
            0xaa => {
                // tableswitch: padded to 4, then default, low, high, and the offsets.
                let base = (pc + 4) & !3;
                let (Some(low), Some(high)) = (i32_at(base + 4), i32_at(base + 8)) else {
                    break;
                };
                let entries = usize::try_from(high - low + 1).unwrap_or(0);
                (Op::Other, base + 12 + entries * 4 - pc)
            }
            0xab => {
                // lookupswitch: padded to 4, then default, npairs, and the pairs.
                let base = (pc + 4) & !3;
                let Some(pairs) = i32_at(base + 4) else {
                    break;
                };
                (
                    Op::Other,
                    base + 8 + usize::try_from(pairs).unwrap_or(0) * 8 - pc,
                )
            }
            0xc4 => (
                Op::Other,
                if code.get(pc + 1) == Some(&0x84) {
                    6
                } else {
                    4
                },
            ),
            _ => (Op::Other, instruction_len(opcode)),
        };
        if op != Op::Other {
            ops.push(op);
        }
        pc += len;
    }
    ops
}

/// Length of every fixed-size instruction.
const fn instruction_len(opcode: u8) -> usize {
    match opcode {
        0x10 | 0x15..=0x19 | 0x36..=0x3a | 0xa9 | 0xbc => 2,
        0x11 | 0x14 | 0x84 | 0x99..=0xa8 | 0xbd | 0xc0 | 0xc1 | 0xc6 | 0xc7 | 0xb4 | 0xb5 => 3,
        0xc5 => 4,
        0xba | 0xc8 | 0xc9 => 5,
        _ => 1,
    }
}
