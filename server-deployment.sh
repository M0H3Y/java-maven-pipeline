#!/usr/bin/bash 
export IMAGE_VERSION=$1
USER=$2
PASS=$3 

echo $PASS | docker login -u $USER --password-stdin
docker-compose up -d 
echo "Success"