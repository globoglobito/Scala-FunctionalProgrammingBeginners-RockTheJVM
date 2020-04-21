package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("COmputing factorial of " + n + " - I need first the factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  }
  println(factorial(10))
  // println(factorial(5000)) stackoverflow


  def anotherfactorial(n: BigInt): BigInt = {
    @tailrec // if not tail recursive then error by compiler
    def factHelper(x: BigInt, accum: BigInt): BigInt = {
      if (x <= 1) accum
      else factHelper(x-1, x * accum) // no need to save intermediary results aka tail recursion
    }
    factHelper(n,1)
  }

  /*
    anotherFactorial(10) = factHelper(10,1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = ...
    = factHelper(2, 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 1)
    = factHelper(1, 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10)
    =  1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10
   */

  //println(anotherfactorial(10))

  // When you need loops, use tail recursion


  // 1) Concatenate a string n times:

  def function1(str : String, n: Int): String = {
    @tailrec
    def f1helper(str2 : String, strAccum: String, accum: Int): String = {
      if (accum == 0) ""
      else if (accum == 1) strAccum
      else f1helper(str2, str2 + strAccum, accum - 1)
    }
    f1helper(str,str,n)
  }
  println(function1("hello", 0))

  // 2) is Prime with tail REC:
      def function2(n: Int): Boolean = {
        def f2helper(t: Int, accum: Boolean): Boolean = {
          if (!accum) false
          else if (t == 1) true
          else f2helper(t-1, n % t !=0 && accum)
        }
        f2helper(n/2, true)
  }

  // 3 Fibonnaci tail rec:

  def function3 (n: Int): Int = {
    def f3helper(i: Int, accum1: Int, accum2: Int): Int = {
      if (i >= n) accum1
      else f3helper(i + 1, accum1 + accum2, accum1 )
    }
    if (n <= 0) 0
    else if (n <= 2) 1
    else f3helper(2,1,1)
  }
  println(function3(15))


}
