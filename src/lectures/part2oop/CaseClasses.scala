package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)
  // what case does:


  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim. name)

  // 2. sensible toString
  println(jim.toString) // otherwise you would get a nice string of letters and numbers as you've seen in the previous ex: ...$Person@7y8g435n
  // println(instances) = println(instances.toString) // syntactic sugar

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // try taking the case out

  //4. have a handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  //5. Have an already implemented companion object
  val thePerson = Person
  val mary = Person("Mary", 23) // this is the apply method of the companion object

  //6. They are serializable

  //7. CC have extractor patterns = CCs can be used in Pattern matching

  case object UnitedKingdom { // case objects also exist, same as case classes but obviously no companion objects
    def name: String = "The UK of GB NI"
  }

  //TL;DR Allows you to create a lot of boiler plate for your classes and some extra bits as dicussed




}
