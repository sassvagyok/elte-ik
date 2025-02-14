$file = Get-Content $args[0]
$number = $args[1]

$sum = 0

foreach ($sor in $file)
{
    if([int]$sor -gt $number)
    {
        $sum += $sor
    }
}

Add-Content -Path $args[0] -Value $sum

Write-Host $sum