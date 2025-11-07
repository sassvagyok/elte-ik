#!/bin/sh

echo 2. feladat:

while read -r line
do
	if [ `echo $line | cut -d" " -f4` -le $1 ]
	then
		echo $line | cut -d" " -f1,2
	fi
done < meresek.dat
