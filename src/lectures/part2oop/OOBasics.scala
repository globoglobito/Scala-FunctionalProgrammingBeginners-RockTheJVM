package lectures.part2oop

object OOBasics extends App{

  val person = new Person("John", 27) // instantiation
  println(person)

  val person2 = new Person2("Pepe", 22)
  println(person2.x) // The reason we get the 4 before the value of x is because every time a class is instantiated everything inside it is evaluated (and executed).
  println(person2.greet("Wombo"))
  println(person2.greet())

  //////////////

  val author1 = new Writer("John", "Doe", 1993 )
  val author2 = new Writer("Charles", "Clayworth", 1993 )
  val novel1 = new Novel("Wololo", 2009, author1)

  println(novel1.authorAge())
  println(novel1.isWrittenBy(author2))

  //////////////

  val counter1 = new Counter()
  counter1.print // should print the default value of 0
  counter1.inc.print // should print 1
  counter1.inc.inc.inc.print // should print the default value + 3 inc (REMEMBER THE  ORIGINAL INSTANCE IS NEVER MODIFIED, we created a new instance when we added a 1)
  counter1.inc(3).print // same as above

}


class Person(name: String, age: Int) // A constructor, meaning that any new instances of person mut be constructed with parameters  name and age

/* class parameters are NOT fields, you cant access with the usual .parameterName.
You would need to add val before the parameter to make it accessible.
 EX: class Person(name: String, val age: Int) to call person.age
 */
class Person2(name: String, val age: Int) {
  //body
  val x = 2 // there are fields
  println(1+3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name") /* This is a method. The 'this' makes reference to the parameter name defined when the class is instantiated.
  This is because  your constructor parameter has the same name as your method parameter, creating ambiguity (no need for this if there is no ambiguity) .
  */
  def greet(): Unit = println(s"Hello my name is $name") // No ambiguity, thus the 'this' is implied

  /* Notice that there is no error for having 2 methods with he same name? Well this is called OVERLOADING.
   This means defining methods with the same name but with different signatures, Scala knows how to interpret this.
   Scala would fail to interpret this if:
      - Both methods have no parameters, thus Scala would not know to which method are you referring to
      -  Both methods have the same type of parameters and number of parameters

   */
  // multiple constructors
  def this(name: String) = this(name,0) // an auxiliary constructor that initiates the class with 0 as age, thus only requiring name. In theory this is really not needed as you can always give your Parameters a default value.
    // tbh not much real use since to create an auxiliary constructor you need another constructor (primary or auxiliary)
}


  /* EX1: Create a Novel and a Writer Class:
  Writer: First name, surname, year
  - method  gives fullname
  Novel: name, year, author
  - AuthorAge
  - isWrittenBy
  - copy (new year of release) = new instance of Novel


 */
class Writer(val firstName: String, val lastName: String, val year: Int) {
  def fullName(): String = s"$firstName " + s"$lastName"
}
class Novel(name: String, year: Int, author: Writer) {
  def authorAge(): Int = year -  author.year

  def isWrittenBy(author: Writer): Boolean =  author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, this.author) // in this case the this is already implied. I just added it to remind us that we are calling the existing parameter

}

/* EX2: Create a Counter Class:
Counter: int value
- method current count
- method to increment/decrement by one => new Counter
- overload inc/dec to receive an amount

*/
class Counter(val count: Int = 0) {
  // No need for the first method, just type yourInstance.count to get the current count.
  def inc = new Counter(count + 1) //  The reason we create a new instance, rather than modify the existing one is immutability. Instances are fix and cannot be modified ... just like vals!!!
  def dec = new Counter(count - 1) //

  def inc(amount: Int): Counter = {
    if (amount <= 0) this // REMEMBER 'this' means this instance! (because if its 0 or negative no need to generate a new instance
    else inc.inc(amount - 1)
  }

  def dec(amount: Int): Counter = {
    if (amount <= 0) this
    else dec.dec(amount - 1)
  }

  def print = println(count) // just to see if this works

}




