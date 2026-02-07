#!/bin/bash

echo "=== Running All Tests ==="
echo "Note: This requires JUnit to be installed"
echo ""

# Simple test runner that compiles and runs test classes
# This is a template - actual implementation depends on test setup

echo "1. Compiling tests..."
find test -name "*.java" > test_sources.txt
javac -d build -cp build:lib/junit.jar @test_sources.txt 2>/dev/null

if [ $? -ne 0 ]; then
    echo "Warning: JUnit may not be installed. Running simple tests via Main..."
    java -cp build Main
else
    echo "2. Running GraphTest..."
    java -cp build:lib/junit.jar org.junit.runner.JUnitCore GraphTest
    
    echo "3. Running BFSTest..."
    java -cp build:lib/junit.jar org.junit.runner.JUnitCore BFSTest
    
    echo "4. Running DFSTest..."
    java -cp build:lib/junit.jar org.junit.runner.JUnitCore DFSTest
    
    echo "5. Running ApplicationsTest..."
    java -cp build:lib/junit.jar org.junit.runner.JUnitCore ApplicationsTest
fi

# Cleanup
rm -f test_sources.txt
echo ""
echo "=== Tests Complete ==="
