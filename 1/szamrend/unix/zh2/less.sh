#!/bin/sh

if [ $# -eq 0 ]
then
	echo Adj meg paramétert!
else
	elozo=$1
	for i in $*
	do
		if [ $elozo -lt $i ]
		then
			echo $elozo
		fi
		elozo=$i
	done
fi
