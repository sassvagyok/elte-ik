Param(
    [Parameter(Mandatory=$true)][string]$testFile
)

if (-not (Test-Path $testFile -PathType Leaf))
{
    Write-Host "A megadott fájl nem létezik!"
}
else
{
    $file = Get-Content $testFile
    for ($i = 0; $i -le $file.Length - 1; $i++)
    {
        if ($i % 2 -eq 0)
        {
            $file[$i] | Out-File odd.txt -Append
        }
        else
        {
            $file[$i] | Out-File even.txt -Append
        }
    }
}