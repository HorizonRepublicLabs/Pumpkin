"""The minimal NBT reader the block-and-chunk smokes share.

Big-endian, uncompressed payload -- enough to walk a chunk's compounds and read a
palette entry. Values come back as plain tuples: ("compound", [(tag, name, value)]),
("list", elem_tag, [values]), ("str", raw_bytes) and so on.
"""
import struct


# --- minimal NBT (big-endian, uncompressed payload) ---------------------------
def nbt_read(buf, pos, tag):
    if tag == 0: return None, pos
    if tag == 1: return buf[pos], pos+1
    if tag == 2: return struct.unpack_from(">h", buf, pos)[0], pos+2
    if tag == 3: return struct.unpack_from(">i", buf, pos)[0], pos+4
    if tag == 4: return struct.unpack_from(">q", buf, pos)[0], pos+8
    if tag == 5: return struct.unpack_from(">f", buf, pos)[0], pos+4
    if tag == 6: return struct.unpack_from(">d", buf, pos)[0], pos+8
    if tag == 7:
        n = struct.unpack_from(">i", buf, pos)[0]; pos += 4
        return ("bytes", buf[pos:pos+n]), pos+n
    if tag == 8:
        n = struct.unpack_from(">H", buf, pos)[0]; pos += 2
        return ("str", buf[pos:pos+n]), pos+n
    if tag == 9:
        elem = buf[pos]; n = struct.unpack_from(">i", buf, pos+1)[0]; pos += 5
        items = []
        for _ in range(n):
            v, pos = nbt_read(buf, pos, elem)
            items.append(v)
        return ("list", elem, items), pos
    if tag == 10:
        out = []
        while True:
            t = buf[pos]; pos += 1
            if t == 0: break
            n = struct.unpack_from(">H", buf, pos)[0]; pos += 2
            name = buf[pos:pos+n]; pos += n
            v, pos = nbt_read(buf, pos, t)
            out.append((t, name, v))
        return ("compound", out), pos
    if tag == 11:
        n = struct.unpack_from(">i", buf, pos)[0]; pos += 4
        return ("ints", buf[pos:pos+4*n]), pos+4*n
    if tag == 12:
        n = struct.unpack_from(">i", buf, pos)[0]; pos += 4
        return ("longs", buf[pos:pos+8*n]), pos+8*n
    raise ValueError("tag %d" % tag)

def nbt_write(v, tag):
    if tag == 1: return bytes([v])
    if tag == 2: return struct.pack(">h", v)
    if tag == 3: return struct.pack(">i", v)
    if tag == 4: return struct.pack(">q", v)
    if tag == 5: return struct.pack(">f", v)
    if tag == 6: return struct.pack(">d", v)
    if tag == 7: return struct.pack(">i", len(v[1])) + v[1]
    if tag == 8: return struct.pack(">H", len(v[1])) + v[1]
    if tag == 9:
        elem, items = v[1], v[2]
        return bytes([elem]) + struct.pack(">i", len(items)) + b"".join(nbt_write(i, elem) for i in items)
    if tag == 10:
        out = b""
        for t, name, val in v[1]:
            out += bytes([t]) + struct.pack(">H", len(name)) + name + nbt_write(val, t)
        return out + b"\x00"
    if tag == 11: return struct.pack(">i", len(v[1])//4) + v[1]
    if tag == 12: return struct.pack(">i", len(v[1])//8) + v[1]
    raise ValueError("tag %d" % tag)

def compound_get(comp, name):
    for t, n, v in comp[1]:
        if n == name: return t, v
    return None, None

