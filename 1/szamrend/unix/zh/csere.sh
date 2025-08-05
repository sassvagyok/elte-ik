#!/bin/sh

cat emailtext.txt | sed s/[.]/" dot "/g emailtext.txt | sed s/[@]/" at "/g emailtext.txt
