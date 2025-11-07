if ($args.Length -gt 0)
{
    $lines = Get-Content $args[0]
}
else
{
    $lines = $input
}

foreach ($line in $lines)
{
    $line.Replace(".", " pont ").Replace("@", " kukac ")
}