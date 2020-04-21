package lectures.part2oop

object AnonymousClass extends App{

  abstract class Animal {
    def eat: Unit
  }
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("top kek") // So you created your anonymous class
  }
  println(funnyAnimal.getClass) // class lectures.part2oop.AnonymousClass$$anon$1   YOUR PROOF IT IS ANONYMOUS

  // What the compiler actually does is the following:
  /* class AnonymousClasses$$anon$1 extends Animal {
        override def eat: Unit = println("top kek")
        }
    val runnyAnimal: Animal = new  AnonymousClasses$$anon$1
   */

  class Person(name: String) {
    def sayHi: Unit = println(s"hello I am $name")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println("hello this is Jim")
  }
  println(jim.getClass) // class lectures.part2oop.AnonymousClass$$anon$2

  val Pepe = new Person("Pepe")
  println(Pepe.getClass) // class lectures.part2oop.AnonymousClass$Person


  class something(name: String) {
    def sayHi: Unit = println(s"hello I am $name")
    def sayNo: Unit = println("No.....")
  }

  val some1 = new something("Juan") {
    override def sayHi: Unit = println("hello this is Juan")
  }

  println(some1.getClass) // class lectures.part2oop.AnonymousClass$$anon$3
    // It seems that the moment you override something, then its a new class. I guess you do this if you want to create a new class but dont want to bother defining it?



}
