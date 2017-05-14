# DijkstrasShortestPathGenerator
A rough implementation of a simple undirected graph that returns the shortest path from a vertex a to z. Made primarily for writing out the steps of the path for homework problems.

This was made to help me easily write out the steps of the problems for my discrete structures class so it's very hastily done.

The main entry point of the program is in DijkstraDemo.java

It can take an input file to generate graphs easily.
When compiling the project in eclipse, you can store the input file in the project root directory (the folder titled "Dijkstra's Shortest Path Generator" 
that also has the bin and src folders in it once you compile).

The input file format is like so:

The first line should be a list of space separated vertex names with a semicolon (also space separated) at the end:

a b c d ;

Subsequent lines should be in the following format:

vertex1

neighborVertex1 neighborEdgeWeight1 neighborVertex2 neighborEdgeWeight2 

Example small graph:

a b c d e z ;

a:

b 4 c 2

b:

c 1 d 5

c:

d 8 e 10

d:

e 2 z 6

e:

z 3

Spacing between lines does not really matter as the string is split with the \s+ regex (so amount of whitespace is irrelevant).
