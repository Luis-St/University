rule stolen_signatures
{
strings:
$find1 = "realtek" nocase
$find2 = "jmicron" nocase
condition:
$find1 or $find2
}
