if ($args.Length -lt 2)
{
    Write-Host Nincs elég paraméter
    exit # kilép a scriptből
}
# else...

[int]$a = $args[0]

Write-Host Összeg: ($args[0] + $args[1])
Write-Host Különbség: ($args[0] - $args[1])
Write-Host Szorzat: ($args[0] * $args[1])
Write-Host Hányados: ($args[0] / $args[1])

Write-Host Hatványozás: ([Math]::Pow($args[0], $args[1]))