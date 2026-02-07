#!/bin/bash

echo "=== Running Graph BFS DFS Assignment ==="
echo "Usage: ./run.sh [mode]"
echo "Modes:"
echo "  1 - Run Main example"
echo "  2 - Run Maze Solver (Part D)"
echo "  3 - Run tests"

# Check if compiled
if [ ! -d "build" ]; then
    echo "Error: Build directory not found. Run ./compile.sh first"
    exit 1
fi

# Run based on argument
if [ "$1" = "2" ]; then
    echo "Running Maze Solver (Part D)..."
    java -cp build applications.MazeSolver
elif [ "$1" = "3" ]; then
    echo "Running tests..."
    # For JUnit tests, you'd need JUnit in classpath
    echo "Note: Run tests individually via IDE or with:"
    echo "  java -cp build:lib/junit.jar org.junit.runner.JUnitCore GraphTest"
else
    echo "Running Main example (default)..."
    java -cp build Main
fi
