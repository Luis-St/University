# Step 8: Recover Deleted Files
## Disk Image: 20260328_server.E01
### Recover Deleted Files (Bulk Recovery, Not reallocated)
`tsk_recover` is `icat` in bulk mode, which can be used to recover all files (including deleted ones) from the image.\
It will create a directory structure in the output directory, and save each file with its inode number as the filename.

```shell
tsk_recover -o 2048 images/20260328_server.E01 images/recovered/
```

### Carve Deleted Files (Reallocated)
If the deleted files have been reallocated (overwritten), they won't be recoverable by `tsk_recover`.\
In that case, you can use file carving tools like `foremost` to scan the raw image for file signatures and recover files based on their content rather than filesystem metadata.\
```shell
foremost -o images/carved/ -i /mnt/ewf_server/ewf1
```
