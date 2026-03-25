# Step 6: Extract / Examine Specific Files
## Disk Image: 20260328_server.E01
### Get inode info

```shell
# 3839 -> /etc/passwd
istat -o 2048 images/20260328_server.E01 3839
# 35816 -> /etc/shadow
istat -o 2048 images/20260328_server.E01 35816
```

#### Output for /etc/passwd

```text
inode: 3839
Allocated
Group: 0
Generation Id: 519099884
uid / gid: 0 / 0
mode: rrw-r--r--
Flags: Extents, 
size: 92
num of links: 1

Inode Times:
Accessed:       2026-03-23 13:03:24.245093551 (CET)
File Modified:  2025-04-19 12:15:06.000000000 (CEST)
Inode Modified: 2026-03-23 13:02:28.765094467 (CET)
File Created:   2026-03-23 13:02:28.765094467 (CET)

Direct Blocks:
89830
```

#### Output for /etc/shadow

```text
inode: 35816
Allocated
Group: 4
Generation Id: 1912634504
uid / gid: 0 / 42
mode: rrw-r-----
Flags: Extents, 
size: 775
num of links: 1

Inode Times:
Accessed:       2026-03-23 13:11:33.325085476 (CET)
File Modified:  2026-03-23 13:11:33.217085477 (CET)
Inode Modified: 2026-03-23 13:11:33.241085477 (CET)
File Created:   2026-03-23 13:11:33.217085477 (CET)

Direct Blocks:
72924
```

### Extract a file by inode number

`icat` can also recover deleted files (marked with `*` in `fls` output) if their data blocks haven't been overwritten yet (not reallocated).

```shell
icat -o 2048 images/20260328_server.E01 3839 > AcquisitionOfKnowledge/passwd.txt
icat -o 2048 images/20260328_server.E01 35816 > AcquisitionOfKnowledge/shadow.txt
```

See `AcquisitionOfKnowledge/passwd.txt` and `AcquisitionOfKnowledge/shadow.txt` for the extracted contents.

### Identify file type

```shell
file AcquisitionOfKnowledge/passwd.txt
file AcquisitionOfKnowledge/shadow.txt
```

#### Output for /etc/passwd

```text
AcquisitionOfKnowledge/passwd.txt: ASCII text
```

#### Output for /etc/shadow

```text
AcquisitionOfKnowledge/shadow.txt: ASCII text
```
