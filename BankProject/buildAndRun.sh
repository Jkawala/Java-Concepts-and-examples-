#!/bin/sh
mvn clean package && docker build -t com.mycompany/BankProject .
docker rm -f BankProject || true && docker run -d -p 9080:9080 -p 9443:9443 --name BankProject com.mycompany/BankProject