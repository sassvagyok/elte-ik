Param(
    [Parameter(Mandatory=$true)]
    [int]$n
)

$fact = 1

for ($n; $n -ge 1; $n--)
{
    $fact *= $n
}

Write-Host $fact