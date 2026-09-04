# Java Race Simulation

A console-based race simulation developed in Java to demonstrate graph algorithms, custom data structures, and event-driven processing.

The race track is represented as a directed graph. Cars travel between checkpoints, where different processing rules such as FIFO, LIFO, MAX Heap, and START priority determine the order in which cars continue the race.

## Features

- Graph-based race track representation
- Event-driven race simulation
- Random road selection between checkpoints
- Multiple checkpoint processing rules
- FIFO processing using a custom queue
- LIFO processing using a custom stack
- Min-Heap and Max-Heap implementations
- Custom event priority queue
- Depth-First Search (DFS) for path existence checks
- Race leaderboard based on finishing time
- Tracking of each car's complete path
- PIT and FINISH checkpoint handling
- Race track data loaded from an external text file

## Project Structure

```text
java-race-simulation/
├── src/
│   ├── Main.java
│   ├── Race.java
│   ├── Car.java
│   ├── Checkpoint.java
│   ├── Road.java
│   ├── Graph.java
│   ├── DepthFirstPaths.java
│   ├── Eventt.java
│   ├── EventList.java
│   ├── EventHeap.java
│   ├── MyHeap.java
│   ├── MyQueue.java
│   └── MyStack.java
├── racetrack.txt
├── .gitignore
└── README.md
```

## Main Components

### Race
Controls the overall simulation, loads the track, creates cars, processes events, manages checkpoints, and generates the final leaderboard.

### Graph
Represents the race track using checkpoints as vertices and roads as directed edges.

### Road
Represents a connection between two checkpoints together with its distance.

### Checkpoint
Processes cars according to different rules:

- `FIFO` → Queue
- `LIFO` → Stack
- `MAX` → Max Heap
- `START` → Min Heap
- `PIT` → Removes cars from the active race
- `FINISH` → Records completed cars

### Car
Stores information about each car, including:

- Car ID
- Current checkpoint
- Elapsed race time
- Race status
- Complete travelled path

### DepthFirstPaths
Uses Depth-First Search to determine whether a path exists between two checkpoints.

### EventHeap
Stores race events in priority order according to arrival time.

### MyQueue
Custom dynamically growing circular queue implementation.

### MyStack
Custom stack implementation used for LIFO checkpoint processing.

### MyHeap
Custom Min-Heap / Max-Heap implementation used to prioritize cars based on their IDs.

## Data Structures & Algorithms

This project demonstrates:

- Graphs
- Depth-First Search
- Priority Queues
- Binary Heaps
- Queues
- Stacks
- Arrays
- Event-driven simulation
- File I/O

## Race Track

The race track is loaded from:

```text
racetrack.txt
```

The file contains the number of checkpoints, number of roads, and the directed connections between checkpoints.

Each road is represented using:

```text
startCheckpoint endCheckpoint distance
```

Example:

```text
0 1 8
0 2 1
0 3 5
```

## How to Run

### Requirements

- Java Development Kit (JDK)

### Compile

From the project root directory:

```bash
javac src/*.java
```

### Run

```bash
java -cp src Main
```

Make sure `racetrack.txt` remains in the project root directory.

## Program Flow

When the program starts:

1. The race track is loaded from `racetrack.txt`.
2. The user enters the number of cars.
3. Cars begin at checkpoint `0`.
4. Cars travel through randomly selected outgoing roads.
5. Each checkpoint processes cars according to its assigned rule.
6. Events are processed according to arrival time.
7. Cars reaching checkpoint `15` finish the race.
8. Cars entering checkpoint `10` are moved to the PIT.
9. A leaderboard is generated after the race.
10. The user can check whether a path exists between two checkpoints using DFS.

## Example Concepts

The simulation combines several data structures in a single application:

```text
Graph        -> race track
DFS          -> path checking
Queue        -> FIFO checkpoints
Stack        -> LIFO checkpoints
Heap         -> priority-based checkpoints
Event Heap   -> chronological race event processing
```

## Purpose

This project was developed as an academic Java project to practice data structures, graph algorithms, custom collection implementations, priority processing, and event-driven simulation.

## Author

**Zeynep Oktay**
