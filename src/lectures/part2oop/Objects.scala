package lectures.part2oop

object Objects extends App {
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static"), check the Java playground
  object Person {
    // "static"/"Class" level functionality
    val N_EYES = 2    // This is how Scala does it its 'static' functionality
    def canFly: Boolean = false
    // Remember apply? well it is a factory method, because it is whole purpose is to build new Persons given some parameters
    def apply(mother: Person, father: Person): Person = new Person(s"child of ${mother.name} and ${father.name}")
  }
  class Person(val name:String) {
    // instance-level functionality
  }
  println(Person.N_EYES)
  println(Person.canFly)
  val mary = Person
  val john = Person
  println(mary == john)
  // new instances of class Person
  val mary2 = new Person("Mary")
  val john2 = new Person("John")
  println(mary2 == john2)
  val child2 = Person(mary2, john2) // remember the syntactic sugar for apply! ... its not an actual constructor...
  println(child2.name)
}
/* Scala Object = Singleton Instance.
 So when you define the object (object Person), not only do you define its type (Person in this case). You define that it's the only instance
 Hence with the val mary, you are merely assigning the existing instance to Mary, not creating a new one. Same thing with val John, if you make an == comparison between Mary and John you'll get True.
 Because both vals are POINTING to the same singleton object

 Objects can be defined in a similar way that Classes can. WITH the big difference that objects cannot receive parameters

 We can also define a class with the same name!
 Because it can allow us to separate our instance functionality (Classes) and our "static" functionality (Objects)

 This pattern of defining classes and objects of the same name is called COMPANIONS. Thus the class Person and object Person are companions because they reside in the same scope and have the same name.

 ONE FINAL NOTE on the OBJECT: WHen we say it is singleton, we refer that object Person is the only instance existing, or rather you can ONLY refer to this single instance.
 HOWEVER, you IF you define a companion class Person, you can still instantiate new classes (still there is ONLy 1 instance of object Person with the 'static methods').

 Scala Applications= Scala object with:
  - EITHER an extend App as our example above OR
  - Adding the following method to our object:
  def main(args: Array[String]): Unit

  Hence, Instead of the 'extend app' you could have written this object as:

object Objects {
    ...
  def main(args: Array[String]): unit = {
    val mary = Person
    val john = Person
    println(mary == john)
    ...
    val child1 = Person.apply(mary2, john2)
    val child2 = Person(mary2, john2)
    println(child2.name)
  }
}
  NOTE: 'extend App' does not works well for jars that are submitted in Spark:
  https://spark.apache.org/docs/latest/quick-start.html#self-contained-applications
  " Note that applications should define a main() method instead of extending scala.App. Subclasses of scala.App may not work correctly."


 */
