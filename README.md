# Kaprekar's Constant #

## Get Started ##

The program takes only one program argument - a four digit integer
with at least two different digits that comprise the number.

From the command line, you should just be able to do something like
`java home.Main 1234` from the root of the `kaprekars-constant`
module.

If you want to run it within an IDE, create a configuration to run
the `home.Main` class and provide a program argument like `1234`.

## Why write this thing? ##

When I learned about it, it seemed like a fun algorithm to develop. I
learned the various parts of a basic subtraction operation:

* Minuend
* Subtrahend
* Difference

It was particularly interesting learning how to convert between a
Java `Character` and the `String` to represent that character. The
back and forth isn't quite as straightforward as it might seem at
first glance.

Also conversion between `String` and `int` objects was important for
this exercise. When differences less than a thousand were computed,
it was _very_ important to pad the integer with leading zeros
because those zeros turn out to be very important in the streaming
operations for computing the minuend and subtrahend values of the
next calculation!

So working with `String` objects to handle leading zeros was
important, but also converting the `String` to `int` was important
for performing actual subtraction and returning the resulting
difference between the computed minuend and subtrahend.

## References and Online Resources ##

* [Subtraction Symbol and Parts](https://byjus.com/maths/subtraction/#Subtraction-Meaning)
* [6174 on Wikipedia](https://en.wikipedia.org/wiki/6174)