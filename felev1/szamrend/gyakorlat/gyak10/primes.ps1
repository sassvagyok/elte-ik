function isPrime([int]$a)
{
    for ($i = 2; $i -lt $a; $i++)
    {
        if ($a % $i -eq 0)
        {
            return $false
        }
    }
    return $true
}

for ($i = $args[0]; $i -le $args[1]; $i++)
{
    if(isPrime($i))
    {
        Write-Host $i
    }
}