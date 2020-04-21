package lectures.part3fp

object HOFCurries extends App {


  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null ///  Just an example to illustrate a HOF parameters
  // High order Function (HOF)

  // map, flatMap, Filter in MyList

  /*function that applies a function n times over a value x.
    nTimes(f,n,x)
    nTimes(f,3,x)  = nTimes(f, 2,f(x)) = = f(f(f(x)))
    nTimes(f,n,x) = f(f(...f(x) = nTimes(f, n-1, f(x))
   */

  def nTimes(f: Int => Int, n: Int, x:Int): Int = {
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne,10,1))


  // ntb(f,n) = x > f(f(f...(x)))
  // increment10 = ntb(plusOne,10) = x => (plusOne ... (x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x:Int) => x
    else (x: Int) => nTimesBetter(f,n-1)(f(x)) /// Basically Calls nTimesBetter with n-1 and apply this to function f(x)
  }

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

/* SOOOOO whats is the big difference between nTimes and NtimesBetter.
 In N times you are cramming the 3 parameters into the function (the function, in this case plusOne, the number of time (10) plusOne will be applied, and the number it will be applied to (1).
 In the other one, you are cramming the function that you'll be calling and the number of times it will be called. Then you are saving this call into a function type (plus10) and then calling plus10 with the value 1.
 */


  // curried functions: functions with multiple parameter lists

  val superAdder: Int=> (Int => Int) = (x: Int) => (y: Int ) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))
 // curry functions are super helpul if youw ant to define helper functions to later apply different values.


  //functions with multiple parameter lists (that acts as curried functions):

  def curriedFormatter(c: String)(x:Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")

  println((standardFormat(Math.PI)))


  //EX:

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =  x => y => f(x,y)

  def fromCurry(f: (Int => Int => Int)) : (Int, Int) => Int = (x,y) => f(x)(y)

  // FunctionX
  def compose[A,B,T](f: A => B, g: T => A ): T => B =
    x => f(g(x))
  def andThen[A,B, C](f: A => B, g: B => C ): A => C =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17))


  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2,times3)

}


