# Step 5: Examine the Filesystem
## Disk Image: 20260328_apl.E01 - 20260328_apl.E07

```shell
fsstat -o <off> images/20260328_apl.E0?
fls -o <off> images/20260328_apl.E0?
fls -r -o <off> images/20260328_apl.E0?
fls -r -d -o <off> images/20260328_apl.E0?
```

## Disk Image: 20260328_server.E01
### Show filesystem details for a partition

```shell
fsstat -o 2048 images/20260328_server.E01
```

```text
FILE SYSTEM INFORMATION
--------------------------------------------
File System Type: Ext4
Volume Name: 
Volume ID: debdc2f65da1d2b7a349816e27a1270f

Last Written at: 2026-03-23 13:19:46 (CET)
Last Checked at: 2026-03-23 13:02:13 (CET)

Last Mounted at: 2026-03-23 13:19:49 (CET)
Unmounted properly
Last mounted on: /

Source OS: Linux
Dynamic Structure
Compat Features: Journal, Ext Attributes, Resize Inode, Dir Index
InCompat Features: Filetype, Needs Recovery, Extents, 64bit, Flexible Block Groups, 
Read Only Compat Features: Sparse Super, Large File, Huge File, Extra Inode Size

Journal ID: 00
Journal Inode: 8

METADATA INFORMATION
--------------------------------------------
Inode Range: 1 - 250481
Root Directory: 2
Free Inodes: 211547
Inode Size: 256

CONTENT INFORMATION
--------------------------------------------
Block Groups Per Flex Group: 16
Block Range: 0 - 1000703
Block Size: 4096
Free Blocks: 676480

BLOCK GROUP INFORMATION
--------------------------------------------
# ext4 divides the disk into Block Groups — fixed-size chunks that each manage
# their own inodes and data blocks. This keeps related data close together on disk.

Number of Block Groups: 31
# The filesystem has 31 block groups (numbered 0–30).
# Total blocks: 31 × 32768 = 1,015,808 (close to the 1,000,704 actual blocks).

Inodes per group: 8080
# Each group can track up to 8,080 files/directories.
# Total inodes: 31 × 8,080 = 250,480 (matches the inode range 1–250,481 above).

Blocks per group: 32768
# Each group spans 32,768 blocks × 4,096 bytes = 128 MiB of disk space.

Group: 0:
# Group 0 is the first block group — it contains the root directory, the primary
# superblock, and most core system directories (/etc, /usr, /var, etc.).

  Block Group Flags: [INODE_ZEROED]
  # INODE_ZEROED = unused inodes in this group have been zeroed out.
  # This means unallocated inodes won't contain leftover data from old files —
  # relevant for forensics: you can't recover inode metadata from zeroed entries.

  Inode Range: 1 - 8080
  # This group manages inodes 1–8080.
  # Inode 1 = bad blocks list, inode 2 = root directory, inode 8 = journal.
  # Inodes 1–10 are reserved by ext4 for special purposes.

  Block Range: 0 - 32767
  # This group covers the first 32,768 blocks (first 128 MiB of the partition).

  Layout:
  # The internal structure of this block group on disk:

    Super Block: 0 - 0
    # Block 0 — the superblock: the master record of the entire filesystem.
    # Contains filesystem size, block size, inode count, mount times, etc.
    # This is what fsstat reads. Backup copies exist in other groups.

    Group Descriptor Table: 1 - 1
    # Block 1 — describes the layout of ALL 31 block groups (bitmap locations,
    # inode table locations, free counts). One entry per group.

    Group Descriptor Growth Blocks: 2 - 489
    # Blocks 2–489 (488 blocks) — reserved empty space so the Group Descriptor
    # Table can grow if the filesystem is resized (online resize feature).

    Data bitmap: 490 - 490
    # Block 490 — a single block (4,096 bytes × 8 bits = 32,768 bits).
    # Each bit = one block: 1 = used, 0 = free.
    # This is how ext4 tracks which blocks are allocated in this group.

    Inode bitmap: 506 - 506
    # Block 506 — same idea but for inodes: one bit per inode.
    # 1 = inode in use (file exists), 0 = inode free.
    # Forensic note: a deleted file flips its bit to 0 here.

    Inode Table: 522 - 1026
    # Blocks 522–1026 (505 blocks) — stores the actual inode structures.
    # Each inode = 256 bytes, so 505 × 4096 / 256 = 8,080 inodes. Matches!
    # The inode table holds: permissions, timestamps, owner, size, block pointers.

    Data Blocks: 8602 - 32767
    # Blocks 8602–32767 (24,166 blocks) — where actual file contents are stored.
    # Note the gap (1027–8601): used by other flex group members or reserved.

  Free Inodes: 0 (0%)
  # Every inode in this group is used — all 8,080 slots are taken.
  # This group is completely full of files/directories.

  Free Blocks: 17625 (53%)
  # 53% of data blocks are free — files here are mostly small (metadata-heavy
  # system files like those in /etc). Lots of room but no free inodes.

  Total Directories: 972
  # 972 directories in this group — this is the root of the filesystem,
  # so it holds /etc, /usr, /var and their many subdirectories.

  Stored Checksum: 0x877C
  # CRC checksum for this group's descriptor entry.
  # ext4 uses this to detect corruption in the group descriptor table.
```

### List files in root directory

```shell
fls -o 2048 images/20260328_server.E01
```

```text
d/d 1232:       home
d/d 11: lost+found
d/d 13: etc
d/d 15: media
l/l 15497:      vmlinuz.old
d/d 20: var
d/d 185:        usr
d/d 1218:       boot
d/d 1219:       dev
d/d 1233:       proc
d/d 1234:       root
d/d 1235:       run
d/d 1236:       sys
d/d 1237:       tmp
l/l 1284:       bin
l/l 1285:       lib
l/l 1286:       lib64
l/l 1287:       sbin
d/d 1253:       mnt
d/d 1254:       srv
d/d 1255:       opt
l/l 15498:      initrd.img.old
l/l 22187:      vmlinuz
l/l 15499:      initrd.img
V/V 250481:     $OrphanFiles
```

### List all files in a directory (e.g., /etc)

The `13` is the inode number for the `etc` directory.

```shell
fls -o 2048 images/20260328_server.E01 13
```

### List all files recursively
Includes all files in all subdirectories, deleted ones are marked with *

```shell
fls -r -o 2048 images/20260328_server.E01
```

List too long to include here, but you will see all files in the filesystem

### List only deleted files

```shell
fls -r -d -o 2048 images/20260328_server.E01
```

Total: **789 deleted file entries**

#### How to read the output format
```text
r/r * 37362(realloc):   etc/apache2/mods-available/log_debug.load.dpkg-new
│ │ │ │                  └── file path within the filesystem
│ │ │ └── (realloc) = inode has been reallocated to a new file, original data likely overwritten
│ │ └── * = file is deleted
│ └── second letter = metadata status (r = regular file, d = directory, l = symlink)
└── first letter = name status (r = regular, d = directory, l = symlink, - = unknown)
```

Other markers you'll see:
- `r/r *` — deleted regular file
- `d/d *` — deleted directory
- `l/l *` — deleted symbolic link
- `-/r *` — name entry gone but metadata (inode) still exists
- `(realloc)` — inode reused for another file, **original content is gone**
- No `(realloc)` — inode not reused, **data may still be recoverable**

---

#### Analysis: What was deleted?

##### 1. dpkg-new files (784 of 789 — the vast majority)
These are **package manager temp files** from `apt`/`dpkg` updates. When Debian updates a
package, it writes new config files as `.dpkg-new`, then renames them into place and deletes
the `.dpkg-new` version. These are **not forensically interesting** — they are normal system
housekeeping.

Affected areas:
- `etc/apache2/mods-available/` — Apache module configs
- `usr/lib/apache2/modules/` — Apache shared libraries
- `usr/lib/grub/i386-pc/` — GRUB bootloader files
- `usr/lib/modules/6.12.73+deb13-amd64/kernel/net/netfilter/` — kernel netfilter modules
- `usr/lib/modules/6.12.74+deb13+1-amd64/kernel/net/netfilter/` — newer kernel netfilter modules
- `usr/share/ca-certificates/mozilla/` — SSL/TLS root certificates
- `usr/share/consolefonts/`, `usr/share/i18n/`, `usr/share/terminfo/` — locale/terminal data
- `usr/share/man/` — man pages
- `usr/share/bash-completion/` — tab completion scripts

**Forensic takeaway:** The server had recent package updates (Apache, kernel, GRUB, CA certs).
Two different kernel versions (6.12.73 and 6.12.74) suggest a kernel upgrade happened.

##### 2. Orphan file (1 file — potentially interesting)
```text
-/r * 131642:   $OrphanFiles/OrphanFile-131642
```
An **orphan file** is a file whose parent directory entry has been removed, but the inode
still exists on disk. TSK collects these under the virtual `$OrphanFiles` directory.

```text
istat output for inode 131642:
  uid/gid:        6 / 12       # uid 6 = typically "man", gid 12 = typically "man"
  mode:           rrw-r--r--
  size:           0            # file is empty (0 bytes)
  created:        2026-03-23 13:22:26
  deleted:        2026-03-23 13:22:26  # created and deleted in the same second
```

This was a **zero-byte temp file** (likely from `man-db` indexing) created and immediately
deleted during a package install. **Not forensically significant.**

##### 3. Metadata entries with inode 0 and 1 (3 entries)
```text
-/- * 1(realloc):   var/lib/dpkg/info/^
-/- * 1(realloc):   usr/share/man/man1/^
-/- * 0:            usr/lib/grub/i386-pc/^
```
The `^` character and `-/-` type indicate **corrupted or partial directory entries**.
These are remnants of directory operations (file creation/deletion) that left behind
incomplete entries. The inodes (0 and 1) are special filesystem inodes, confirming
these are artifacts, not real files.

---

#### Summary

| Category | Count | Forensically Interesting? |
|----------|-------|--------------------------|
| `.dpkg-new` temp files (package updates) | 784 | No — normal apt/dpkg housekeeping |
| Orphan file (man-db temp) | 1 | No — zero-byte temp file |
| Corrupted directory entries | 3 | No — filesystem artifacts |
| **User-deleted files** | **0** | — |

**Key finding: No user files were deleted on this server.**
All 789 deleted entries are system-level artifacts from package management.
This means either:
1. No one manually deleted files on this server
2. Deleted user files were overwritten to the point that even the directory entries are gone
3. Anti-forensic tools were used to wipe deletion traces

To dig deeper, you could:
- **Carve unallocated space** with `foremost` to find file fragments without directory entries
- **Examine the journal** (`icat -o 2048 images/20260328_server.E01 8 > journal.dat`) for traces of deleted operations
- **Check swap space** (partition 003 at offset 8007680) for memory fragments
