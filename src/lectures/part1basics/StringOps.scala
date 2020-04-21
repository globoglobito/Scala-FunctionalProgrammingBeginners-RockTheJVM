package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, we have a, long string"

  println(str.charAt(2)) // returns char at that position. REMEMBER we start at 0
  println(str.substring(7,11)) // returns the substring starting at position 7 until (inclusive) position 11
  println(str.split(",").toList) // returns a list where the string is separated by the input character, in this case its a ','.
  println(str.startsWith("Hello")) // Returns Boolean value if the string begins with the input string
  println(str.replace(" ", "-")) // Returns a new String but with - instead of white spaces
  println(str.toUpperCase()) // Returns a new String with ALL CAPS (toLowerCase also exists!)
  println(str.length) // Returns length of the string


  val aNumberString = "49" // again Strings can have numeric chars
  val aNumber = aNumberString.toInt // if its valid, you can turn the string into an Integer
  println("abc" +: aNumberString) // to prepend a string (or char) to another string (or char)
  println(aNumberString + "def") // to append!
  println(str.reverse) // self explanatory
  println(str.take(2)) // substring that takes the first n chars

  // Scala-specific: String interpoletors

  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello my name is $name and I am ${age + 5}" // basically allows you to place variables inside your strings.

  println(greeting)

  // F-interpolators

  val speed = 1.2f //  a float
  val myth =  f"$name can eat $speed%2.2f per min" // basically here we are forcing a change in float number format. Specifically the %2.2f means 2 Character total, minimum 2 decimal precision
  println(myth)

  // raw-interpolator
  println(raw"This is a \n newline") // print as is, but can accept variables, but if you place a variable in there, said variable will not be raw
  val ex = "hello \n you"
  println(raw"This is a \n newline. $ex")



  //

}
