#!/bin/sh

szorzat=`expr $2 \* $3`

if [ $szorzat -eq $1 ]
then
	echo egyenlo
else
	echo nem egyenlo
fi
