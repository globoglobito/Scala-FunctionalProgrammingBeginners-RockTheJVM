package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    //def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}" //original method
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the frick?"  // SUPER IMPORTANT there must be a white space between the method name
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"

  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // Thanks to the magic of Scala this is equivalent!
  // infix notation or operator notation ONLY works with methods with ONE parameter. These infix notations are called syntactic sugar


  // "operators" in scala
  val tom = new Person("Tom", "Fight Club")
 // println(mary hangOutWith tom) original name
   println(mary + tom) // Scala can assign almost any name to a method.

  // What about  + for ints? Well + is also a method
  println(1+2)
  println(1.+(2))

  //ALL OPERATORS ARE METHODS, hence the "operators"


  // prefix notation
  val x = -1
  val y = 1.unary_- // this means the same


  // unary_prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  // postfix notations
  println(mary.isAlive)
  println(mary isAlive)   // rarely used in practice, since the postfix notation can cause ambiguity to the reader.

  // apply ONLY (very important!)
  println(mary.apply())
  println(mary()) // equivalent

  /*

   */


}
