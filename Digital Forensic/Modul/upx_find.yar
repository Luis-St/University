rule upx_find
{
strings:
$upx0 = "upx0" nocase
$upx1 = "upx1" nocase
$rsrc = ".rsrc" nocase
condition:
$upx0 and $upx1 and $rsrc
}
