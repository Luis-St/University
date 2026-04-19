# Step 3: Mount the Image (Read-Only)
## Disk Image: 20260328_server.E01
### Setup Mount Point and Mount EWF Image

```shell
sudo mkdir -p /mnt/ewf_server
sudo ewfmount images/20260328_server.E01 /mnt/ewf_server
```

### Work with the Mounted Image
After mounting, you will find a raw disk image at `/mnt/ewf_server/ewf1`.\
You can analyze this raw image with tools like `mmls`, `fsstat`, `fls`, etc., using the appropriate offsets for partitions.
Like this:

```shell
strings /mnt/ewf_apl/ewf1 | grep -i "password"
strings /mnt/ewf_apl/ewf1 | grep -i "secret"
```

### Clean Up: Unmount the Image

```shell
sudo umount /mnt/ewf_server
```
