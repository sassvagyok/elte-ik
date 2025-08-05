#!/bin/sh

echo osszeg: `expr $1 + $2`

x=`expr $1 + $2`
echo osszeg: $x

echo kulonbseg: `expr $1 - $2`

echo szorzat: `expr $1 \* $2`

echo hanyados: `expr $1 / $2`
