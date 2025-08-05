#!/bin/sh

file=`cat szam.dat`

case $2 in
	-a)
		muv=`expr $file + $1`
		echo $muv
		echo $muv > szam.dat
		;;
	-s)
		muv=`expr $file - $1`
		echo $muv
	        echo $muv > szam.dat
		;;
	-m)
		muv=`expr $file \* $1`
	        echo $muv	
		echo $muv > szam.dat
		;;
	-d)
		muv=`expr $file / $1`
		echo $muv
		echo $muv > szam.dat
		;;
esac
