package lectures.part2oop

import java.nio.BufferOverflowException

object Exceptions extends App {

  val x: String = null
  //println(x.length) Null Pointer Exception, it will crash.

  // 1. throwing and catching exceptions

  //val aWeirdValue = throw new NullPointerException // Exceptions are instances of classes, hence the new keyword. Here you are purposely 'causing' and exception and assigning it to a val.


  // throwable clases extend the Throwable class
  // Exception and Error are the major Throwable subtypes.
  // Exceptions denote something that went wrong with the program, Errors denote something that went wrong with the system.

  // 2. How to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 45

  try{
    // code that might throw an exception
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a runtime exception")
  } finally {
    // CODE THAT WILL GET EXECUTED NO MATTER WHAT
    // finally is optional and does not
    println("finally")
  }

  /* because the runtime exception thrown by our getint function was caught, it did not crash the jvm.
  We were expecting this behaviours and thus had a response to it if surfaced
  Also see the finally, it will run irregardless of what happens (caught or not)
  Of course case "e: RuntimeException" will only catch RuntimeExceptions... not a nullpointer....
   */

  // 3. How to define your own Exceptions

  class MyException extends Exception
  val exception = new MyException
  // println(exception) You will throw your exception



  /*How to crash the JVM with OutOfMemory
  val array = Array.ofDim(Int.MaxValue)
   */



  // Pocket Calculator
  class OverflowException extends Exception("Int max value exceeded")
  class UnderflowException extends Exception("Int min value exceeded")
  class MathCalculationException extends Exception("Division by Zero")
  object PocketCalculator {

    def add(x: Int, y: Int)= {
    try {
      if (x > 0 && y > 0 && x + y < 0) throw new OverflowException
      else if (x < 0 && y < 0 && x + y > 0) throw new UnderflowException
      else x + y
    } catch {
      case e: OverflowException => println(e)
      case f: UnderflowException => println(f)
    }
    }

    def substract(x: Int, y: Int)= {
      try {
        if (x > 0 && y < 0 && x - y < 0) throw new OverflowException
        else if (x < 0 && y > 0 && x - y > 0) throw new UnderflowException
        else x - y
      } catch {
        case e: OverflowException => println(e)
        case f: UnderflowException => println(f)
      }
    }

    def multiply(x: Int, y: Int)= {

      try {
        if (x > 0 && y > 0 && x + y < 0) throw new OverflowException
        else if (x < 0 && y < 0 && x + y < 0) throw new OverflowException
        else if (x > 0 && y < 0 && x + y > 0) throw new UnderflowException
        else if (x < 0 && y > 0 && x + y > 0) throw new UnderflowException
        else x * y
      } catch {
        case e: OverflowException => println(e)
        case f: UnderflowException => println(f)
      }
    }
    def division(x: Int, y: Int)= {
      try {
        if (y == 0) throw new MathCalculationException
        else x / y
      } catch {
        case e: MathCalculationException => println(e)
      }
    }

  }

  val test1 = PocketCalculator.add(2147483647, 5)
  println(test1)
  val test2 = PocketCalculator.substract(-2147483647, 5)
  println(test2)
  val test3 = PocketCalculator.multiply(2147483644, 2147483642)
  println(test3)
  val test4 = PocketCalculator.division(2147483647, 0)
  println(test4)

  println(2147483647 * 5)

}
