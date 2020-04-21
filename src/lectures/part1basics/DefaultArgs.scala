package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  // Recall or function for factorial, specifically the helper function that did everything. The accumulator param is needed, even through it will be 1 for 99% of the cases and thus we use a wrapper function that only accepts one parameter.
  def anotherfactorial(n: BigInt): BigInt = {
    @tailrec // if not tail recursive then error by compiler
    def factHelper(x: BigInt, accum: BigInt): BigInt = {
      if (x <= 1) accum
      else factHelper(x - 1, x * accum) // no need to save intermediary results aka tail recursion
    }

    factHelper(n, 1)
  }

  // However, we can change this so that you can call the necessary parameter, deleting the need for a wrapper function.
  def trFactDefault(x: BigInt, accum: BigInt =1): BigInt = {
    if (x <= 0) 0 // we need to add this distinction, as factorial of 0 is not 1!
    else if (x == 1) accum
    else trFactDefault(x - 1, x * accum)
  }
  println(trFactDefault(0)) /// Tadaaaaa


  // OK, but what if you have multiple parameters with defaults and you just want to  change one! The compiler wont be able to determine which  param is it referring to... unless
  def doSomething(x: String = "Wololo", y: Int = 22 , z: Int = 420 ): Unit = println("I did something!  " + x + y + z )
  doSomething(z = 2)
  doSomething(z = 3, x = "Pepe") // order can be disregarded!!!!
  doSomething(x = "Flour", z = 11 ) // ... you add name name of the parameter to the call

}
