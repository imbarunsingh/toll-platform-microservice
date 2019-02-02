#!/bin/sh
echo "Processing removal of all the Dangling Docker Images"

#Adding all the non-dangling docker images to a file: non-dangling-image-list.txt
docker images -a | grep -v "<none>" | awk "{print \$1}" | sed '1d' >> non-dangling-image-list.txt

#Backing up each non-dangling image in a .tar
COUNT=0
while read line; do
  docker save --output backupimage-$COUNT.tar $line
  echo "Backing up Image :" $line
  let COUNT++
done < non-dangling-image-list.txt

#Removing all the docker images in the machine
docker image prune -fa

#Removing the temporary file
rm -rf non-dangling-image-list.txt

#Recovering each of the non-dangling docker image from .tar created for each of them
a=0
while (("$a" < "$COUNT"))
do
  docker load --input backupimage-$a.tar
  echo "Loaded image:" $a
  rm -rf backupimage-$a.tar
  a=`expr $a + 1`
done