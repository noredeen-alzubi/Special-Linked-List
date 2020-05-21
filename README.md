# Special-Linked-List
A doubly linked list data structure with a two-way sentinel. Not the most useful data structure, but it sets up interesting approaches to shuffling/sorting/other algos.

## The Data Structure:
This is a normal doubly linked list with a modified sentinel node added. This sentinel also has the "doubly" features and works just like any other node (an instance of the Node class). This creates a closed circle structure which could allow us to loop through the nodes endlessly by repeatedly getting the next node.

The sentinel is flipped so the circle structure can make sense but having either orientation would be fine. Its value is null so we can identify it from the rest of the nodes.

Adding and removing from the list (or closed circle) is a bit tedious, but the many pointers create easy and convenient retrieval and access.
<p align="center">
<img alt="ppt" src="https://user-images.githubusercontent.com/30037359/50381341-64095780-068d-11e9-9efa-080e683a2f15.png">
</p>

## The Shuffle Algorithm:
This implementation was inspired by [a problem from Stanford's CS9 class](https://web.stanford.edu/class/cs9/sample_probs/ListShuffling.pdf).

It's a more random approach to the **_"perfect shuffle"_**. According to the problem from CS9, a list is perfectly shuffled by "cutting it at the halfway point, then interleaving the two halves by alternating back and forth between the cards". What my shuffle adds is that the "halfway point" is not necessarily the half and could be any random place in the list except the beginning and end. For more randomness, the shuffle is run a random number from 6-10 times. 

In summary, here is the algorithm:

1. Pick a random position in the list as the splitting point.
2. perform the perfect shuffle by interleaving the two sides/halves.
3. Repeat the steps above a random number of times (6-10 times)

<p align="center">
<img alt="ppt" src="https://user-images.githubusercontent.com/30037359/50393055-3a6b3180-075c-11e9-95a3-81125a0829de.jpg">
</p>





