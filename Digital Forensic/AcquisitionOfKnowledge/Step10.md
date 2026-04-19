# Step 10: Timeline Analysis
## Disk Image: 20260328_server.E01

```shell
fls -r -m "/" -o 2048 images/20260328_server.E01 > AcquisitionOfKnowledge/body.txt
mactime -b AcquisitionOfKnowledge/body.txt > AcquisitionOfKnowledge/timeline_root.csv
```

or in one line:

```shell
fls -r -m "/" -o 2048 images/20260328_server.E01 | mactime -b - > AcquisitionOfKnowledge/timeline_root.csv
```

or only of a specific folder (inode, `/home` in this case):

```shell
fls -r -m "/" -o 2048 images/20260328_server.E01 1234 | mactime -b - > AcquisitionOfKnowledge/timeline_home.csv
```

#### Output (timeline_home.csv)

```csv
Mon Mar 02 2026 22:50:00      132 m... r/rrw-r--r-- 0        0        1248     /.profile
                              607 m... r/rrw-r--r-- 0        0        1249     /.bashrc
Mon Mar 23 2026 13:02:31      132 ..cb r/rrw-r--r-- 0        0        1248     /.profile
                              607 ..cb r/rrw-r--r-- 0        0        1249     /.bashrc
Mon Mar 23 2026 13:03:18      132 .a.. r/rrw-r--r-- 0        0        1248     /.profile
Mon Mar 23 2026 13:03:30     4096 .a.b d/drwx------ 0        0        6624     /.ssh
Mon Mar 23 2026 13:15:30      607 .a.. r/rrw-r--r-- 0        0        1249     /.bashrc
Mon Mar 23 2026 13:21:53       20 macb r/rrw------- 0        0        30       /.lesshst
Mon Mar 23 2026 13:24:28      142 m..b r/rrw-r--r-- 0        0        44       /.ssh/known_hosts.old
Mon Mar 23 2026 13:24:32      142 .ac. r/rrw-r--r-- 0        0        44       /.ssh/known_hosts.old
                              978 m.cb r/rrw------- 0        0        48       /.ssh/known_hosts
                             4096 m.c. d/drwx------ 0        0        6624     /.ssh
Mon Mar 23 2026 13:25:00      978 .a.. r/rrw------- 0        0        48       /.ssh/known_hosts
```
