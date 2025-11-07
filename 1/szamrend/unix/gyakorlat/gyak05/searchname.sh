#!/bin/sh

getent passwd | cut -d":" -f5 | cut -d" " -f2 -s | grep $1 | wc -l
