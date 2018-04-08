# FL

1 step: cd ArythmeticParser

build: 
gradle build

gradle installDist 

run tests: gradle test

run application: ./build/install/ArythmeticParser/bin/ArythmeticParser <PATH_TO_FILE>

console output: should be the only integer - a result of an arythmetic expression.