# Step 2: Inspect Image Metadata
## Disk Image: 20260328_apl.E01 - 20260328_apl.E07

```shell
ewfinfo images/20260328_apl.E01
ewfinfo images/20260328_apl.E02
ewfinfo images/20260328_apl.E03
ewfinfo images/20260328_apl.E04
ewfinfo images/20260328_apl.E05
ewfinfo images/20260328_apl.E06
ewfinfo images/20260328_apl.E07
```

## Disk Image: 20260328_server.E01

```shell
ewfinfo images/20260328_server.E01
```

```text
ewfinfo 20140814

Acquiry information
# Metadata recorded at the time the forensic image was created.
# This is entered by the examiner during acquisition and stored inside the E01 file.

        Case number:            1
        # Identifier for the forensic case this image belongs to

        Examiner name:          hpm
        # Initials/name of the person who performed the acquisition

        Evidence number:        2
        # This is the 2nd piece of evidence in case #1
        # (evidence #1 is likely the "apl" workstation image)

        Acquisition date:       Mon Mar 23 15:01:32 2026
        # When the imaging process started

        System date:            Mon Mar 23 15:01:32 2026
        # Clock time of the acquisition machine — matches acquisition date here,
        # but a mismatch could indicate a misconfigured system clock (relevant for timeline analysis)

        Operating system used:  Linux
        # OS running on the forensic workstation (not the evidence machine!)

        Software version used:  20140816
        # Version of the acquisition tool (e.g., ewfacquire from libewf)

        Password:               N/A
        # EWF supports optional password protection — not used here

EWF information
# Technical details about how the image is stored in the E01 container.

        File format:            EnCase 6
        # EWF format version — EnCase 6 (.E01) is the most widely supported format

        Sectors per chunk:      64
        # Data is stored in chunks of 64 sectors = 64 × 512 = 32 KiB per chunk
        # Each chunk is compressed and checksummed independently

        Error granularity:      64
        # If a read error occurs during acquisition, this many sectors are marked as bad
        # Same as chunk size here — errors are tracked per chunk

        Compression method:     deflate
        # zlib/deflate compression (same algorithm as gzip/ZIP)

        Compression level:      best compression
        # Maximum compression — slower acquisition but smaller image file
        # That's why the 4 GiB disk fits into an 898 MB .E01 file

Media information # Details about the original source disk that was imaged.

        Media type:             fixed disk # A fixed (internal) hard drive, as opposed to removable media (USB, CD, etc.)

        Is physical:            yes
        # A full physical disk was imaged (not just a single partition or logical volume)

        Bytes per sector:       512
        # Standard sector size — each sector holds 512 bytes

        Number of sectors:      8396344
        # Total sectors on the original disk: 8,396,344 × 512 = 4,298,928,128 bytes

        Media size:             4.0 GiB (4298928128 bytes)
        # Original uncompressed disk size
        # This is a small disk — likely a virtual machine or a small server disk

Digest hash information
        MD5:                    b934f3f06e9bfecec3d4ae6714fcfe69
        # MD5 hash computed over the entire raw disk content during acquisition.
        # Used to verify integrity: if you hash the raw data again and get the same
        # value, the image has not been altered since acquisition.
        # This is what ewfverify checks — crucial for chain of custody.
```
