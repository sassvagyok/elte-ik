$lines = Get-Content $args[0]

foreach ($l in $lines)
{
    Write-Host ($l.Split(" ", [StringSplitOptions]::RemoveEmptyEntries)[1])
}