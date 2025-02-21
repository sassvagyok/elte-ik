$kisebb = 0
$nagyobb = 0
$lnko = 0

if ($args[0] -lt $args[1])
{
    $kisebb = $args[0]
    $nagyobb = $args[1]
}
else
{
    $kisebb = $args[1]
    $nagyobb = $args[0]
}

while (($kisebb -gt 1) -and ($args[0] % $kisebb -ne 0) -or ($args[1] % $kisebb -ne 0))
{
    $kisebb--
}

Write-Host $kisebb

<#
for ($i = 1; $i -le $kisebb; $i++)
{
    if ($kisebb % $i -eq 0 -and $nagyobb % $i -eq 0)
    {
        $lnko = $i
    }
}
#>

#Write-Host $lnko