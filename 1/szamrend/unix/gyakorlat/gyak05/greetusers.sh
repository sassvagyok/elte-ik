#!/bin/sh

for i in `who  | cut -d" " -f1`
do
	echo Szia $i
done

for i in `seq 10`
do
	echo $i
done
