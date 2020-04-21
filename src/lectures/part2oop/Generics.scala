package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    /* Use the Type A. This is a Generic Class, this means instead of locking this class to a particular type such as Int or String.
      You can use any type you see fit. For example if you remember myList exercise, it was a Int only class. With generics it could be a generic list that you can implement any type.
      It is now +A due to the exercise, but it started as A originally if you get lost.
     */
    def add [B >: A](element:B): MyList[B] = ??? // take another type parameter B which is  a supertype of A, element type B, return a mylist of B.
    /*
    A = Cat
    B = Animal
     */

  }

  class MyMap[Key, Value] // here we are using 2 type parameters
  trait Example[Wololo] // works with trait as well

  val listofIntegers = new MyList[Int]
  val listofStrings = new MyList[String]

  //generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  //VARIANCE problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // If cat extends animal, does a list of cats extends a list of animals?

  // Yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A] // the +A means its a covariant List
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  /* animalList.add(new Dog) ??? Hard Question. Its defined to be a list of Animal so yes,
   BUT its actually a List of Cat... then you are polluting it => Therefore in doing so we return a list of Animals no longer a list of cats

   Here We can replace a list of Animals for a List of Cats... because Cats are animals

   */

  // NO = INVARIANCE

  class InvariantList[A] // thats the default, no symbol
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // cat would trow an error

  // HELL NO CONTRAVARIANCE

  class ContravariantList [-A] // -A means its contravariant
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // Here we are replacing a list of Cats for a List of animals... even though Cats are a subtype of Animal... this really doesn't make sense right?
  // However now watch this example:
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
/* Here we are saying that we want a trainer of Cats, but we Are supplying a Trainer of Animals
  This us actually better because a Trainer of Animals can Train Dogs, Fishes, etc ... and still train cats
  Now this makes sense right?
 */

  // bounded types: allow you to use your generics classes only for certain types that are a subclass of another type or a superclass of a different type
  class Cage[A <: Animal](animal: A) // class cage only accept that A that are a subclass of Animal. This is an upper bounded type
  val cage = new Cage(new Dog) // acceptable
  //class Car generic type need proper bounded type
  //val newCage = new Cage(new Car) // compiler will throw an error
  //class Cage[A >: Animal](animal: A) // lower bounded type. This means A only accept types that are superclasses of Animal


  // expand MyList to be generic:





}
