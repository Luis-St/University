# Step 4: Analyze the Partition Layout
## Disk Image: 20260328_apl.E01 - 20260328_apl.E07

```shell
mmls images/20260328_apl.E01
mmls images/20260328_apl.E02
mmls images/20260328_apl.E03
mmls images/20260328_apl.E04
mmls images/20260328_apl.E05
mmls images/20260328_apl.E06
mmls images/20260328_apl.E07
```

## Disk Image: 20260328_server.E01

```shell
mmls images/20260328_server.E01
```

When the image is mounted as a raw device (e.g., at /mnt/ewf_server/ewf1):
```shell
mmls /mnt/ewf_server/ewf1
```

```text
DOS Partition Table
# The disk uses a DOS/MBR (Master Boot Record) partition scheme.
# MBR is the older standard (vs. GPT). Supports up to 4 primary partitions and disks up to 2 TiB.

Offset Sector: 0
# The partition table starts at sector 0 (the very first sector of the disk).
# This sector is the MBR — it contains the boot loader code and the partition table.

Units are in 512-byte sectors
# All Start/End/Length values below are in sectors of 512 bytes.
# To convert to bytes: multiply by 512. To convert to MiB: multiply by 512 / 1048576.

      Slot      Start        End          Length       Description

000:  Meta      0000000000   0000000000   0000000001   Primary Table (#0)
# "Meta" = metadata entry, not a real partition.
# This is the MBR itself — 1 sector (512 bytes) at the very start of the disk.
# Contains the partition table (4 entries of 16 bytes each) and boot code.

001:  -------   0000000000   0000002047   0000002048   Unallocated
# "-------" = unallocated space (not assigned to any partition).
# 2048 sectors = 1 MiB of empty space before the first partition.
# This is standard alignment — modern tools leave 1 MiB at the start for
# proper sector alignment (improves performance on SSDs and 4K-sector drives).

002:  000:000   0000002048   0008007679   0008005632   Linux (0x83)
# The main Linux data partition.
# "000:000" = primary partition #0 in slot #0.
# Starts at sector 2048, length = 8,005,632 sectors = ~3.8 GiB.
# Type 0x83 = standard Linux filesystem (likely ext4).
# THIS IS THE PARTITION YOU WANT TO ANALYZE — use offset 2048 with TSK tools:
#   fls -o 2048 images/20260328_server.E01
#   fsstat -o 2048 images/20260328_server.E01

003:  000:001   0008007680   0008386559   0000378880   Linux Swap / Solaris x86 (0x82)
# The swap partition — Linux's equivalent of a Windows pagefile.
# "000:001" = primary partition #1 in slot #0.
# 378,880 sectors = ~185 MiB.
# Type 0x82 = Linux swap space.
# Forensically interesting: swap can contain fragments of memory —
# passwords, decrypted data, application state that was paged out.
# You can search it with: strings -o /mnt/ewf_server/ewf1 | grep -i "keyword"
# (scoped to the swap offset range)

004:  -------   0008386560   0008396343   0000009784   Unallocated
# Trailing unallocated space at the end of the disk.
# 9,784 sectors = ~4.8 MiB.
# This is slack space — could contain remnants of a previously larger partition
# or data from before the disk was partitioned.
# Can be carved with foremost/scalpel for hidden or deleted data.
```
