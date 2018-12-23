# Special-Linked-List
A doubly linked list data structure with a two-way sentinel. Not the most useful data structure, but it creates interesting approaches to shuffling/sorting/ other algos.

## The Data Structure:
This is a normal doubly linked list with a modified sentinel node added. This sentinel also has the "doubly" features and works just like any other node (an instance of the Node class). This creates a closed circle structure which could allow us to loop through the nodes endlessly by repeatedly getting the next node.

The sentinel is flipped so the circle structure can make sense but having either orientation would be fine. Its value is null so we can identify it from the rest of the nodes.

Adding and removing from the list (or closed circle) is a bit tedious, but the many pointers create easy and convenient retrieval and access.

![presentation1](https://user-images.githubusercontent.com/30037359/50381341-64095780-068d-11e9-9efa-080e683a2f15.png)

