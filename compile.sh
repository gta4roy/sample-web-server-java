#!/bin/sh
JAVA_HOME=/home/abhijit/tools/jdk-25.0.2
GRADLE_HOME=/home/abhijit/tools/gradle-9.3.1

export JAVA_HOME=${JAVA_HOME}
export GRADLE_HOME=${GRADLE_HOME}

$GRADLE_HOME/bin/gradle clean bootJar
