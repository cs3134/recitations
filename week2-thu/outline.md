# Outline for Week 2 Friday Recitation
***
## What is Git

Source code version control system.
* Great for teams
* Great for keeping track of changes/undoing mistakes/YOU WIL NEVER ACCIDENTALLY REMOVE EVERYTHING AND NEVER BE ABLE TO GET IT BACK!!

## What is GitHub
Git repository hosting service with a great web-based GUI (!!). We can controll access, teams can track bugs, put in feature requests, etc.

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

Private public method idiom (public method accessing private method)

***

## Abstract Data Types and Data Structures

ADT Examples: Stack, queue, etc.

DataStructures: LinkedList, Tree, arrays

An abstract data type is kind of like a theoretical interface. All stacks must be able to pop/push, and are always FIFO. But, I could implement a stack use many data structures--LinkedList, array,etc. Some data structures will make more sense for implementing a given ADT, but nonetheless the ADT could probably be implemented in many ways.

***

## Linked List

Code a linked list from scratch yay! Start with

- `BadNode.java`
- `BadList.java`
- `Iterator.java (coming soon)`
- `BetterList.java`

Private Classes: Why does HW1 have classes inside of classes?
* Encapsulation, hiding implementation details, best representing the theoretical structure. Node makes no sense outside of the context of linked lists.
* Objects can end up in bad states. As independent public classes, we either need to make a lot of instance variables public (bad idea) or make public getters/setters for those instance variables. Setters and getters could probably help avoid some disastrous situations, but not all, because the user REALLY shouldn't have access to all of this functionality. They shouldn't be able to access to Node etc. Let's look at what variables and/or functions now need to be public:

  * LinkedList needs to get and modify Node's data and next instance variables. So now getData() setData(), etc etc are public.
  * Iterator somehow needs to access the head of the linkedlist.

  * Now look at a disaster that could ensue if the user had access to this stuff:
```bash
//Assume list has more than five things in it
Node listHead = myList.getHead();
Node anotherNode = listHead.getNext(); 
for (int i = 0; i < 5; i++) 
  anotherNode = anotherNode.getNext();
listHead.setNext(anotherNode); // OOPS. We just lost a bunch of nodes, and now list size is completely wrong!! This breaks SO MUCH OTHER CODE.
``` 

So, we merge into `BetterList.java`.


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
