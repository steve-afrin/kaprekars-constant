# Kaprekar's Constant

## Getting Started

### Prerequisites

This project is built on Java 17, but it is compatible with Java 11
as well. There is nothing specific in this project that is later
than Java 11.

The program takes only one program argument - a four digit integer
with at least two different digits that comprise the number.

### Executing This Program

Change your directory to the root of the project and then perform
the following commands.

* `./gradlew clean jar` (if you are in a linux environment)
* `.\gradle.bat clean jar` (if you are in a Windows environment)
* `java -jar build/libs/kaprekars-constant-1.0.jar 1234`

### Additional Things to Know

* `./gradlew clean jar sourceJar javadocJar test` will build the
  executable JAR file as well as the sources JAR file and the
  Javadoc JAR file. It will also run tests to make sure all tests
  pass successfully.

* The generated artifacts (JAR files) will be located in the
  `build/libs` directory under the root folder of the project.

* The executable JAR is designed to build as what is commonly called
  a _fat JAR_ or _uber JAR_. This simply means that the executable
  JAR file contains all the dependent libraries the program needs to
  do its job.
    * This bloats the JAR file to 16.5 MB in size whereas without
      the dependent libraries, the JAR file would be only 4 KB in
      size.
    * The actual program is very small, but the dependent libraries
      make the program seem like it is very large and therefore
      complex.
    * Without the dependent libraries the base program cannot
      successfully execute.

* If you want to run the program within an IDE, create a
  configuration to run the `home.Main` class and provide a program
  argument like `1234`. Then execute that configuration to your
  heart's content changing the program argument value as you please.

## Why write this thing?

When I learned about Kaprekar's Constant, it seemed like a fun
algorithm to develop. Creating numbers that order the digits from
largest to smallest and then from  smallest to largest immediately
seemed like an interesting challenge.

One way to do it is to use mathematical operations to extract each
digit and then order those digits. This is provided by the
`KaprekarsNumericCalculator` implementation class.

Another way to do it is to convert the integer to a sequence of the
characters that represent the digits in the number and use a
[Java stream](https://www.baeldung.com/java-8-streams-introduction)
to go over all the individual characters of the String and order
them. This implementation is provided by the
`KaprekarsStringCalculator` class.

It is worthwhile noting that while both implementation classes use
Java streams to reorder the digits in each computed integer, the
numeric implementation does the ordering only once whereas the
String implementation does it twice, but is able to take advantage
of the `Comparator` class in the base JDK to indicate desired
ordering of the digits in the resulting number.

### The Parts of a Subtraction Operation

This problem also presents the opportunity to learn the various
parts of a basic subtraction operation:

* The minuend
* The subtrahend
* The difference

### The Numeric Implementation

There wasn't nearly the amount of converting back and forth between
`int` and `String` as the String implementation had, but there
seemed to be other efficiencies, too, working only numerically to
get the minuend and subtrahend values.

We had to stream only once to get both the minuend and subtrahend
for each number in the algorithm because the only sorting allowed on
an `IntStream` is the natural ordering. So the resulting array has
the digits in sorted order and getting the most significant and
least significant digits was only an issue of indexing on the array
of sorted digits.

### The String Implementation

The String implementation of this algorithm also presented some
interesting learning opportunities. It was particularly interesting
examining how to convert between a Java `Character` instance and the
`String` object to represent that character. The back and forth
isn't quite as straightforward as it might seem at first glance.

Conversion between `String` and `int` objects was crucial for this
exercise. When differences less than a thousand are computed, it is
_very_ important to pad the integer with leading zeros because those
leading zeros (commonly called _padding_) turn out to be very
important in the streaming operations for computing the minuend and
subtrahend values of the next calculation!

So working with `String` objects to handle the padding (a.k.a.
leading zeros) was important, but also converting the `String` to
`int` was important for performing actual subtraction operations and
returning the resulting difference between the computed minuend and
subtrahend.

## References and Online Resources

* [Subtraction Symbol and Parts](https://byjus.com/maths/subtraction/#Subtraction-Meaning)
* [6174 on Wikipedia](https://en.wikipedia.org/wiki/6174)
* [IntStream API from JDK 17](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/IntStream.html)
