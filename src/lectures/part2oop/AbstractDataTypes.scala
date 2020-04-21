package lectures.part2oop

object AbstractDataTypes extends App {

  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal{
    override val creatureType: String = "Canine"
    def eat: Unit = println("Omnomnomnom") // Note: The override keyword is not really needed

  }

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    def eat: Unit = println("yummy")
    def eat (animal: Animal): Unit = println(s"I'm a croc and Im eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc.eat(dog)




}




/* Abstract classes: Classes that contain unimplemented or abstract fields (denoted by the abstract attribute).
    You do this because, sometimes you want the subclasses to define them.
    You cannot instantiate abstract classes (How would the runtime know if the methods and fields aren't instantiated? They are meant to be extended later.

  Traits: The ultimate abstract data types. Unlike abstract classes they can be inherited along classes.
    Look at class Crocodile, it is inheriting from both Animal, Carnivore, and ColdBlooded (you can inherit from as many traits as you want)

  Traits vs Abstract Classes:
    1) Traits cannot have constructor parameters.
    2) You can inherit as many Traits as you want, but you can only extend ONE class(single class inheritance)
    3) traits are behaviors, but abstract class is a type of "thing"



 */