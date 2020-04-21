package lectures.part2oop

object Inheritance extends App {

  class Animal {
    val creatureType = "Wild"
    def eat = println("nomnomnom") // public in java
    private def ex: Boolean = true
    protected def greet: Unit = println("hello!")
  }

  class Cat(name: String) extends Animal {
    def crunch = {
      greet // the protected modifier allows the method ex2 to be used INSIDE the subclass as well
      println("crunch crunch")
    }
  }

  val cat = new Cat("Pusheen")
  cat.eat
  //cat.ex this wont work
  cat.crunch
  //cat.greet still you can't call it outside


  //constructors

  class Person(name:String, age: Int)
  class Adult(name:String, age: Int, idCard: String) extends Person(name, age)
  //or
  class person2(name:String, age: Int) {
    def this(name: String ) = this(name,0)
  }
  class Adult2(name:String, age: Int, idCard: String) extends Person2(name)

  //overriding

  class Dog extends Animal {
    override def greet = println("Woof Woof")
    override val creatureType: String = "Domestic"
    override def eat = println("EATING")
  }
  //or
  class Dog2(override val creatureType: String) extends Animal {
    override def greet = println("Woof Woof")
  }
  val dog = new Dog
  val dog2 = new Dog2("Domestic")
  dog.greet
  dog2.greet // no longer protected!

  // type substitution (polymorphism)

  val unkownAnimal: Animal = new Dog()
  unkownAnimal.eat // its not the same eat as the one defined in animal is it?

  // super

  class Dog3 extends Animal {
    override def eat = {
      super.eat
      println("EATING")}
  }
  val dog3 = new Dog3
  println(dog3.eat)

  /*preventing overrides:
    1 - Use 'final' on either a class ( final class Animal) or to a member of the class (final def eat...).
     With the former it prevents class Animal from being extended, from the latter it prevents subclasses from overriding eat.
    2 - Seal the class =  Able to extend the class in THIS FILE ONLY, but prevent extends in other files (sealed class Animal)
   */




}



/*
  In this example, Cat is a subclass of Class Animal. Hence Animal is called a superClass.
  Scala, like JAVA only accepts SINGLE class inheritance, and only inherits NON-PRIVATE members of the superclass.
  Scala has the Private and Protected modifiers:
  - Private: only accessible by instances of THIS CLASS ONLY
  - Protected: accessible by instances of this class AND by subclasses (but not called outside).

  Overriding: Means to supply a different implementation in derived classes. Overriding works for both methods and vals/var.
  REMEMBER ITS DIFFERENT TO OVERLOADING, which means supplying different methods with the same name but different implementations in the same class!

  Type substition:
  Even if unkownAnimal is declared as type Animal, in actuality it is an instance of Dog. Thus it will always go to the most overridden method if possible)

  Super is used when you want to reference a method or field from a parent class, see the example in class Dog3

 */