#!/bin/bash

rm -r bin/
mkdir bin
find . -name "*.java" > sources
javac -cp src/ -d bin/ @sources
rm sources
java -cp bin/ edu.virginia.lab1test.LabOneGame
