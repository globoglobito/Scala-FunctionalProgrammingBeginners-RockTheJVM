package lectures.part1basics

object Functions extends App {


  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }
  println(aFunction("hi", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n : Int) : String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n -1)
  }

  println(aRepeatedFunction("hello", 3))
  // WHEN YOU NEED LOOPS, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString) // Unit

  def aBigFunction(n: Int): Int =  {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n-1)

  }

  // A greeting function (name, age) => "Hi, I am $name and im $age old"
  def function1(name: String, age: Int): String = s"Hi, I am $name and I'm $age old"
  println(function1("Weh", 8))

  // A factorial function 1 * 2 * 3 * 4
  def function2(n: Int):Int = {
    if (n <= 0) 0
    else if (n == 1) 1
    else n * function2(n-1)
  }
  println(function2(5))

  //A fibonacci function
  def function3 (n: Int): Int = {
    if (n == 0) 0
    else if (n == 1) 1
    else if (n == 2) 1
    else if (n == 3) 2
    else function3(n-1) + function3(n -2)
  }

  println(function3(17))

  // is prime number

  def function4(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t !=0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n/2)
  }
  println(function4(3))
  println(function4(2003))
  println(function4(37*17))



}
