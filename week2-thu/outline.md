# Outline for Week 2 Friday Recitation

## What is Git

Expanation on the concepts of git

## What is GitHub

Explanation of what GitHub is and does

## What The Hell Did We Make You Do In `hw0`

Walkthrough of what we did to in `setup.sh`, including changing remotes. Demonstration of what happened manually (step by step) and why homework pulling works the way it does.

## Java Coding Review (Stuff You Should Know)

Primitives, generics, inheritance, pointers.

Why we need generics? Start with

- `BoxString.java` that contains a String and outputs the item
- `BoxInteger.java` that contains an integer, but does the same thing
- `BoxGeneric.java` that uses generics to handle any type of data

## Java Best Practices (Stuff You Would Want To Know)

StringBuilder, proper IO (using BufferedReader and BufferedWriter)

Inheritance vs Interfaces: What's the difference between the keywords "extends" and "implements"?
*Both avoid us duplicating a lot of code
*Inheritance example: Superclass "Animal," instance variables: type, name, age. Subclass "Dog" w/ method "bark()." Code shared traits/methods ONCE, and in sublcass we only have to put what's unique to that type.

*Interfaces example: Interface "ModeOfTransportation." Classes that implement ModeOfTransportation: Train, Plane, FerryBoat, Car. All have setDestination() boardPassengers(), depart(), changeSpeed(int newSpeed), arrive(), disembarkPassengers(), etc etc.
  *Now, we can do things like make a Travel class that has methods like goToDestination(String destination) and returnHome(). This removes SO MUCH duplicate code b/c we don't need a separate goToDestination() inside Plane, FerryBoat, etc.

Private public method idiom (public method accessing private method)

## Abstract Data Types and Data Structures

What is ADT vs data structure. Don't see myself taking more than 2 min here.

## Linked List

Code a linked list from scratch yay! Start with

- `LinanNode.java`
- `LinanList.java`
- `LinanIterator.java`

Introduce idea of private classes and hiding implementation details. User shouldn't have access to Node etc. Merge into `LinanList.java`

### Singly Linked List

The above will be a singly linked list. Talk about singly list deletion and problems. Circular list etc.

### Doubly Linked List

Talk about doubly linked list, advantage, brief sketch of homework.

### Common Problems and Strategies

Circular linked list, finding mid point in a singly linked list / doubly linked list, deletion in singly linked list in single traversal etc all sorts of stupid interview questions.
