#!/bin/sh

mkdir target

export m2_repo="$HOME/.m2/repository"
export thrift="$m2_repo/org/apache/thrift"
export slf4j="$m2_repo/org/slf4j"

export classpath="$thrift/libthrift/0.9.3/libthrift-0.9.3.jar"
export classpath="$classpath;$slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar;./target"

javac -d target -cp "$classpath" StartClient.java start/*.java

java -cp "$classpath" StartClient $1
