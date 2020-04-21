package lectures.part3fp

object WhatsAFunction extends App{

  // DREAM: use functions as first class elements.
  // PROBLEM: OOP

  trait MyFunction[A,B] {
    def apply(element: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // function types = Function1[A,B] (up to 22)
  val stringtoIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringtoIntConverter("3") + 4) // 7

//Function types are shorthands for class types that define apply functions
  val adder: ((Int, Int ) => Int) = new Function2[Int, Int, Int] {  /// Function type that takes 2 ints and returns an int
    override def apply(a: Int, b: Int): Int = a + b
  }
  // REMEMBER FunctionN denotes the numbe rof types from Function1 (one parameter and one result), Function2 (2 parameters, one result) until Function22
  // Function Types Function2[A,B,Result] === (A,B) => Result

  // ALL SCALA FUNCTIONS ARE OBJECTS AKA INSTANCES OF CLASSES DERIVED FROM FUNCTION1, FUNCTiON2, etc etc

  // a function which takes 2 strings and concatenates them
  val concatenator: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  // A Function which takes an int and returns another function which takes an int and returns an int.
  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  val adder3 = superAdder(3) // this is basically apply(3) = new Function1[int,int], you only defined the x value
  println(adder3(4)) // Now you providing Y
  println(superAdder(3)(4)) // THis is the same as above and its called a curried function. Curried functions can be called with multiple parameter lists






}
