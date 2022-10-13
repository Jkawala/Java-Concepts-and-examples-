@echo off
call mvn clean package
call docker build -t com.mycompany/BankProject .
call docker rm -f BankProject
call docker run -d -p 9080:9080 -p 9443:9443 --name BankProject com.mycompany/BankProject