# Step 7: Mount the Filesystem (Read-Only Browsing)
## Disk Image: 20260328_server.E01
### Setup Mount Point
Step 3 must be completed before proceeding with this step, as it involves mounting the EWF image to access the raw disk image.

```shell
sudo mkdir -p /mnt/forensic
sudo mount -o ro,loop,offset=$((2048 * 512)) /mnt/ewf_apl/ewf1 /mnt/forensic
```

The offset `2048` must be multiplied by the sector size (512 bytes) to get the correct byte offset for mounting.
### Browse the Mounted Filesystem

All tools can be used on the mounted filesystem at `/mnt/forensic` as if it were a normal directory.

```shell
ls /mnt/forensic/
grep -rl "keyword" /mnt/forensic/
```

### Clean Up: Unmount the Filesystem

```shell
sudo umount /mnt/forensic
```
