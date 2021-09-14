#!/usr/bin/bash 
export IMAGE_VERSION=$1
docker-compose up -d 
echo "Success"