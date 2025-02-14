$lines = Get-Content $args[0]

for ($i = $lines.Length - 1; $i -ge 0; $i--)
{
    Write-Host $lines[$i]
}

# [Array]::Reverse($lines)
# Write-Host $lines