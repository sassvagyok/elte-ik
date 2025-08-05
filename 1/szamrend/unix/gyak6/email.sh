#!/bin/sh

case $1 in
	-a)
		echo $2:$3 >> email.dat
		;;
	-d)
		cat email.dat | grep -v ^$2: > tmp
		mv tmp email.dat
		;;
	-c)
		cat email.dat | wc -l 
		;;
	-o)
		echo o kapcsolo
		;;
	*)
		cat email.dat | grep ^$1: | cut -d":" -f2
		;;
esac
