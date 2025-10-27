#!/bin/sh

osszeg=0

i=1

while read -r line
do
	for j in $*
	do
		if [ $i -eq $j ]
		then
			osszeg=`expr $osszeg + $line`
		fi
	done
	i=`expr $i + 1`	
done < numbers.txt

echo $osszeg
