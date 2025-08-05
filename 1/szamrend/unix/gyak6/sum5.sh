#!/bin/sh

sum=0
n=0
while [ $n -lt 5 ]
do
	read sor
	sum=`expr $sum + $sor`
	n=`expr $n + 1`
done

echo $sum
