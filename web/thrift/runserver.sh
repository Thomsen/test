#!/bin/sh

mkdir target

export m2_repo="$HOME/.m2/repository"
export thrift="$m2_repo/org/apache/thrift"
export slf4j="$m2_repo/org/slf4j"

export classpath="$thrift/libthrift/0.9.3/libthrift-0.9.3.jar"
export classpath="$classpath;$slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar;$slf4j/slf4j-log4j12/1.7.5/slf4j-log4j12-1.7.5.jar;$m2_repo/log4j/log4j/1.2.16/log4j-1.2.16.jar;./target"

javac -d target -cp "$classpath" StartServer.java CalculatorHandler.java start/*.java

java -cp "$classpath" StartServer
