"""Report inferium_crop palette entries and the age values stored beside them."""
import sys, zlib, glob, struct, collections
ages = collections.Counter(); crops = 0
for path in glob.glob(sys.argv[1] + "/*.mca"):
    data = open(path, "rb").read()
    if len(data) < 8192: continue
    for i in range(1024):
        off = int.from_bytes(data[i*4:i*4+3], "big") * 4096
        if off == 0 or off + 5 > len(data): continue
        length = struct.unpack(">I", data[off:off+4])[0]
        comp = data[off+4]
        try:
            raw = zlib.decompress(data[off+5:off+4+length]) if comp == 2 else data[off+5:off+4+length]
        except Exception:
            continue
        # every palette entry: ... Name -> "...inferium_crop"; Properties {age: "N"}
        start = 0
        while True:
            j = raw.find(b"inferium_crop", start)
            if j < 0: break
            crops += 1
            # the age string tag nearby: 08 00 03 'age' 00 01 <digit>
            k = raw.find(b"\x03age\x00\x01", max(0, j - 300), j + 300)
            if k >= 0:
                ages[chr(raw[k + 6])] += 1
            start = j + 1
print(f"palette entries naming inferium_crop: {crops}")
print("ages seen:", dict(sorted(ages.items())))
