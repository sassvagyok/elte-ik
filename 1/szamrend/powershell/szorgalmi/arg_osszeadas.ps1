$osszeg = 0

for($i = 0; $i -lt $args.Length; $i++)
{
    $osszeg += $args[$i]
}

Write-Host $osszeg