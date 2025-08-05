#!/bin/sh

while read -r line
do
	echo Sor: $line
done < $1 #input file

