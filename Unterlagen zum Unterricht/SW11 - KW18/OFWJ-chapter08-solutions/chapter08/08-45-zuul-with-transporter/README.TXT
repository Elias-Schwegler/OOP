Project: zuul-with-transporter
Authors: Michael Kolling and David J. Barnes

This project is supplementary material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   David J. Barnes and Michael Kolling
   Pearson Education, 2016

This project implements the solution to exercise 9.9 in the book.

A transporter room is added by creating a subclass of room, in which 
the 'getExit' method has been redefined to return a random room.

For sake of cohesion, a new class 'Scenario' has also been introduced
since now all rooms must be held during the game play. Doind this in
a special purpose class is nicer than holding them in the 'Game' class.
