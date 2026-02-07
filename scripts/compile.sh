#!/bin/bash

echo "=== Compiling Graph BFS DFS Project ==="

# Create build directory if it doesn't exist
mkdir -p build

# Find all Java files and compile
find src -name "*.java" > sources.txt
javac -d build @sources.txt

# Check if compilation succeeded
if [ $? -eq 0 ]; then
    echo "Compilation successful! Classes in build/"
else
    echo "Compilation failed!"
    exit 1
fi

# Clean up
rm sources.txt

echo "=== Ready to run: java -cp build Main ==="
