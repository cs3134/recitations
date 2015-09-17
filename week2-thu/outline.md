# Outline for Week 2 Friday Recitation
***
## What is Git

Expanation on the concepts of git

## What is GitHub

Explanation of what GitHub is and does


## What The Hell Did We Make You Do In `hw0`
Walkthrough of what we did to in `setup.sh`, including changing remotes. Demonstration of what happened manually (step by step) and why homework pulling works the way it does.
<p>
![alt text](https://github.com/cs3134/recitation-devo/blob/master/week2-thu/git-diagram.png "HW-diagram")


## Submissions and New Homework Instructions

### To Submit
```bash
$ git add --all
$ git commit -m "whatever message"
$ git push origin master
```

###To Get New Homework (and Homework Solutions)
 ```bash
$ git pull upstream master
 ```

**Note that when you get new homework, you pull from  `upstream `. When you submit, you push to  `origin `.**

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

An abstract data type is kind of like a theoretical interface. All stacks must be able to pop/push, and are always FIFO. But, I could implement a stack use many data structures--LinkedList, array,etc. Some data structures will make more sense for implementing a given ADT, but nonetheless the ADT could probably be implemented in many ways.

***

## Linked List

Code a linked list from scratch yay! Start with

- `Node.java`
- `List.java`
- `Iterator.java`
- `BetterList.java`

Private Classes: Why does HW1 have classes inside of classes?
- Encapsulation, hiding implementation details, best representing the theoretical structure. Node makes no sense outside of the context of linked lists.
- Objects can end up in bad states. As three separate files, we need to make variables or methods public that really shouldn't be public. Ex: LinkedList needs to get and modify Node's data and next instance variables. Iterator needs to access the head of the linkedlist, and also needs to access that node's next and data in order to iterate through the list and return each node's data. So, we either have to make these instance variables in Node and LinkedList public or use setters and getters. Setters and getters could probably help avoid some disastrous situations, but not all, because the user REALLY shouldn't have access to all of this functionality. They shouldn't be able to access to Node etc. Then, people can do disastrous things like:

```bash
Node head = myList.getHead();
Node anotherNode = head.getNext(); 
for (int i = 0; i < 5; i++) 
  anotherNode = anotherNode.getNext();
head.setNext(anotherNode); // OOPS. Now list size is completely wrong!! off by 5. this break SO MUCH OTHER CODE.
``` 

All of these methods above would have to be public (Iterator needs getHead to access list items, list and iterator both need to getNext() of a node, and also need to setNext in order to add/remove items). BUT THIS CAN CREATE HUGE PROBLEMS. List doesn't even know that it changed.

So, we merge into `BetterList.java`.



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
