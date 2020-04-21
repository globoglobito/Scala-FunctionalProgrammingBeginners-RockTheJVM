package exercises

object MethodNotationsEX extends App {

  class Person(val name: String, val favouriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favouriteMovie

    //def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}" //original method
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the frick?" // SUPER IMPORTANT there must be a white space between the method name
    def isAlive: Boolean = true // postfix
    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"

    //EX
    def +(st: String): Person = {
      if (st == "the rockstar") new Person(this.name + s" ($st)", this.favouriteMovie)
      else this
    }
    def unary_+ : Person = new Person(name, favouriteMovie, age + 1) // Remember no need to add this. if there is no ambiguity

    def learns(subject: String): String = s"$name learns $subject"
    def learnsScala: String = learns("Scala") // Again no need to add .this, it is implied

    def apply(num: Int) : String = s"$name watched $favouriteMovie $num times"

  }

  // 1. Overload the + operator --> Mary + "the rockstar" into a new Person => Mary (the rockstar)
  val mary = new Person("Mary", "Inception")
  val tom = new Person("Tom", "V")
  val maryRock = mary + "the rockstar"
  println(maryRock())
  println(mary + tom) // still works!

  /*2. Add an age to the Person class:
   Add a unary + operator => new person with the age + 1
   +mary => mary with the age incremented
   */

  val maryAge = +mary
  println(maryAge.age)



  /* Add a "learns" method in the person class => "Mary learns Scala"
     Add a learnScala method, calls learns method with "Scala"
     Use it in postfix notation
   */
  println(mary.learns("Music"))
  println(mary learnsScala)

  /*
   Overload the apply method
   mary.apply(2) --> "Mary watched inception 2 times"
   */
 println(mary.apply(2))
 println(mary())



}
