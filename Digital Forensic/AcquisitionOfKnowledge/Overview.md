# Digital Forensic Disk Image Analysis — Overview

## Your Images

| File | Type | Size | Description |
|------|------|------|-------------|
| `20260328_apl.E01`–`.E07` | EWF (Expert Witness Format) | ~12.5 GB total (7 segments) | Split forensic image — likely a workstation/laptop ("apl") |
| `20260328_server.E01` | EWF | ~898 MB | Single-segment forensic image — a server |
| `md5s.txt` | Text | MD5 hashes for integrity verification |

EWF (`.E01`) is the EnCase image format. It stores a bit-for-bit copy of a disk along with metadata (case info, acquisition timestamps, checksums). Split segments (`.E01`–`.E07`) are just the same image split at 2 GB boundaries.

---

## Your Installed Tools

| Tool | Status | Purpose |
|------|--------|---------|
| **ewfinfo / ewfmount / ewfverify** | Installed | Inspect, mount, and verify EWF images |
| **The Sleuth Kit** (mmls, fsstat, fls, icat, istat, img_stat, blkcat, blkls, ffind, ifind, sigfind, sorter, tsk_recover) | Installed | Partition/filesystem analysis, file recovery |
| **Autopsy** | Installed | GUI frontend for The Sleuth Kit |
| **Volatility 3** (vol) | Installed | Memory forensics (not directly applicable to disk images) |
| **foremost / scalpel** | Installed | File carving (recover files by header/footer signatures) |
| **strings / file / hexdump / xxd** | Installed | Low-level inspection |
| **md5sum / sha256sum** | Installed | Hash verification |
| **fdisk / parted** | Installed | Partition table inspection |
| bulk_extractor | **Missing** | `sudo apt install bulk-extractor` |
| testdisk / photorec | **Missing** | `sudo apt install testdisk` |
| binwalk | **Missing** | `sudo apt install binwalk` |
| hashdeep | **Missing** | `sudo apt install hashdeep` |

---

## What You Can Do — Step by Step

### 1. Verify Image Integrity
Before anything else, confirm the images weren't corrupted during transfer.

```bash
# Verify internal EWF checksums
ewfverify images/20260328_apl.E01
ewfverify images/20260328_server.E01

# Compare against provided MD5 hashes
md5sum images/20260328_apl.E01 images/20260328_apl.E02 ...
# Then compare output with md5s.txt
```

### 2. Inspect Image Metadata
```bash
# Shows acquisition date, examiner, case number, compression, etc.
ewfinfo images/20260328_apl.E01
ewfinfo images/20260328_server.E01
```

### 3. Mount the Image (Read-Only)
```bash
# Mount EWF as a raw device
sudo mkdir -p /mnt/ewf_apl
sudo ewfmount images/20260328_apl.E01 /mnt/ewf_apl
# This creates /mnt/ewf_apl/ewf1 — a raw disk image you can work with

# When done:
sudo umount /mnt/ewf_apl
```

### 4. Analyze the Partition Layout
```bash
# Show partition table (works directly on .E01 files)
mmls images/20260328_apl.E01
mmls images/20260328_server.E01

# Or on the mounted raw image:
mmls /mnt/ewf_apl/ewf1
```
This tells you the offset of each partition — you need the offset for the next steps.

### 5. Examine the Filesystem
```bash
# Show filesystem details for a partition (replace OFFSET with the sector offset from mmls)
fsstat -o OFFSET images/20260328_apl.E01

# List files in root directory
fls -o OFFSET images/20260328_apl.E01

# List all files recursively (including deleted ones, marked with *)
fls -r -o OFFSET images/20260328_apl.E01

# List only deleted files
fls -r -d -o OFFSET images/20260328_apl.E01
```

### 6. Extract / Examine Specific Files
```bash
# Get inode info
istat -o OFFSET images/20260328_apl.E01 INODE_NUMBER

# Extract a file by inode number
icat -o OFFSET images/20260328_apl.E01 INODE_NUMBER > recovered_file.dat

# Identify file type
file recovered_file.dat
```

### 7. Mount the Filesystem (Read-Only Browsing)
```bash
# After ewfmount, mount a specific partition read-only
sudo mkdir -p /mnt/forensic
sudo mount -o ro,loop,offset=$((OFFSET * 512)) /mnt/ewf_apl/ewf1 /mnt/forensic

# Now browse like a normal filesystem
ls /mnt/forensic/

# Unmount when done
sudo umount /mnt/forensic
```

### 8. Recover Deleted Files
```bash
# Recover all deleted files from a partition
tsk_recover -o OFFSET images/20260328_apl.E01 output_dir/

# Or carve files by signature (finds files even without filesystem metadata)
foremost -o carved_output/ -i /mnt/ewf_apl/ewf1
```

### 9. Search for Keywords / Strings
```bash
# Search for readable strings in the raw image
strings /mnt/ewf_apl/ewf1 | grep -i "password"
strings /mnt/ewf_apl/ewf1 | grep -i "secret"

# Or use grep on the mounted filesystem
grep -rl "keyword" /mnt/forensic/
```

### 10. Timeline Analysis
```bash
# Generate a body file (TSK timeline format)
fls -r -m "/" -o OFFSET images/20260328_apl.E01 > body.txt

# Convert to a readable timeline
mactime -b body.txt > timeline.csv
```

### 11. Use the Autopsy GUI
```bash
# Launch Autopsy (web-based GUI for Sleuth Kit)
autopsy
# Then open http://localhost:9999/autopsy in your browser
```
Autopsy provides a point-and-click interface for all of the above — great for exploring and for generating reports.

---

## Key Concepts Cheat Sheet

| Concept | Meaning |
|---------|---------|
| **EWF / E01** | Expert Witness Format — compressed forensic image with metadata and checksums |
| **Sector** | Smallest addressable unit on disk (usually 512 bytes) |
| **Offset** | Partition start position in sectors — needed by most TSK tools (`-o` flag) |
| **Inode** | Filesystem metadata entry pointing to a file's data blocks |
| **Deleted file** | Inode/directory entry marked as free, but data may still exist on disk |
| **File carving** | Recovering files by scanning for known headers/footers (no filesystem needed) |
| **Chain of custody** | Documenting every action taken on evidence to ensure admissibility |
| **Write blocker** | Hardware/software preventing writes to evidence (EWF images are inherently read-only) |
| **Hash verification** | Proving the image hasn't been altered since acquisition |
| **MAC times** | Modified / Accessed / Changed timestamps on files — basis for timeline analysis |

---

## Suggested Learning Path

1. **Verify** → Run `ewfverify` and check MD5s (integrity is everything in forensics)
2. **Explore metadata** → `ewfinfo` to understand what was acquired
3. **Partition layout** → `mmls` to see what's on the disk
4. **Filesystem overview** → `fsstat` and `fls` to browse the contents
5. **Deleted files** → `fls -d` and `tsk_recover` to find what was removed
6. **Deep dive** → Extract interesting files with `icat`, carve with `foremost`
7. **Timeline** → Build a timeline to reconstruct events
8. **Autopsy** → Use the GUI for a more visual exploration and reporting
