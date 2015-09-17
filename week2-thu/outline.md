# Outline for Week 2 Friday Recitation

## What is Git

Expanation on the concepts of git

***

## What is GitHub

Explanation of what GitHub is and does

***

## What The Hell Did We Make You Do In `hw0`
Walkthrough of what we did to in `setup.sh`, including changing remotes. Demonstration of what happened manually (step by step) and why homework pulling works the way it does.

***

## Java Coding Review (Stuff You Should Know)

Primitives, generics, inheritance, pointers.

Why we need generics? Start with

- `BoxString.java` that contains a String and outputs the item
- `BoxInteger.java` that contains an integer, but does the same thing
- `BoxGeneric.java` that uses generics to handle any type of data

***

## Java Best Practices (Stuff You Would Want To Know)

StringBuilder, proper IO (using BufferedReader and BufferedWriter)

Inheritance vs Interfaces: What's the difference between the keywords "extends" and "implements"?
* Both avoid us duplicating a lot of code
* Inheritance example: Superclass "Animal," instance variables: type, name, age. Subclass "Dog" w/ method "bark()." Code shared traits/methods ONCE, and in sublcass we only have to put what's unique to that type.

* Interfaces example: Interface "ModeOfTransportation." Classes that implement ModeOfTransportation: Train, Plane, FerryBoat, Car. All have setDestination() boardPassengers(), depart(), changeSpeed(int newSpeed), arrive(), disembarkPassengers(), etc etc.
  * Now, we could make a Travel class that has methods like goToDestination(String destination) and returnHome(). This removes SO MUCH duplicate code b/c we don't need a separate goToDestination() inside Plane, FerryBoat, etc.
  * All other future code we make involving travel can now just do "myFerryBoat.goToDestination()," even though the goToDestination() method isn't in the FerryBoat class.


Private public method idiom (public method accessing private method)

***

## Abstract Data Types and Data Structures

ADT Examples: Stack, queue, etc.

DataStructures: LinkedList, Tree, arrays

An abstract data type is kind of like an theoretical interface. All stacks must be able to pop/push, and are always FIFO. But, I could implement a stack use many data structures--LinkedList, array,etc. Some data structures will make more sense for implementing a given ADT, but nonetheless the ADT could probably be implemented in many ways.

***

## Linked List

Code a linked list from scratch yay! Start with

- `Node.java`
- `List.java`
- `Iterator.java`
- 'BetterList.java'

Introduce idea of private classes and hiding implementation details. User shouldn't have access to Node etc. Merge into `LinanList.java`

### Singly Linked List

The above will be a singly linked list. Talk about singly list deletion and problems. Circular list etc.

### Doubly Linked List

* deleteNode(Node n) is O(1)
  * All we need to do is:
    n.next.prev = n.prev;
    n.prev.next = n.next; 

  * With Singly-Linked list, we'd have to go through the list until we hit the node whose "next" is n, and then change references.

* BUT delete(T x) is still O(n) for double-linked lists because we need to find the node whose data is "x"

* Reverse is kind of more fun: just swap each node's next and prev (remember to use a temp!). Still O(n) but easier to think about and doesn't make you draw a bunch of absurd diagrams to make sure you're not botching it (but still, it would definitely be a good idea for you to try writing pseudocode for HOW you would do this for a singly-linked list b/c it would be great partice for the class/interviews).

* Of course, we now have more modifications we need to make when we insert/mess with the list, but it's generally worth it.

* Does take up more space b/c we have a "prev" reference.

### Common Problems and Strategies

####Circular linked list
Last Node's next is the first node
* Don't have to worry about moving off the end of the list (null pointers). It's always okay to derefence node.next.data 
* When we insert and delete, it's all the same. No first/list problems.
* Kind of cool: Let's say my iterator object is at the last item and I want to now go to the second item. We can just do "iterator.next(); iterator.next();" without making a new iterator.


####Finding mid point in a singly linked list / doubly linked list (when you don't know size)
* Singly-Linked: i = head; j = head; increment i every time, j every other time until i hits the end of the list
* Doubly-Linked: i = head; j = tail; wait till they converge

####Deletion in singly linked list in single traversal etc all sorts of stupid interview questions.
