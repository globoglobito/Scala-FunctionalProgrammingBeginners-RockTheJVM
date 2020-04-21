package lectures.part1basics

object CallbyNameCallbyValue extends App {


  def callbyValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def callbyName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  callbyValue(System.nanoTime())
  callbyName(System.nanoTime())

  /*
  Lets take the values we get from this functions:
  by value: 1146020620397300
  by value: 1146020620397300
  by name: 1146020700828300
  by name: 1146020700851300

  As you can see, call by value evaluates the parameter the moment the functions is called, because the parameter is a value:
  callbyValue(System.nanoTime()) ->>>> callbyValue(1146020620397300)
    println("by value: " + 1146020620397300)
    println("by value: " + 1146020620397300)

  On the contrary, call by name evaluates the parameter each time it is called, it is an expression and thus it is evaluated at every use within (notice that param x is different each time it is evaluated:
  callbyName(System.nanoTime())
    println("by name: " + 1146020700828300)
    println("by name: " + 1146020700851300)

   */

  // Another example how both interact:
  def infinity(): Int = 1 + infinity()
 // def printFirstParam(x: Int, y: Int) = println(x) // This will cause a stack overflow as both params are evaluated at the same time. Try it if you dont believe it
 def printFirstParam(x: Int, y: => Int) = println(x) // This will work! Because it delays the evaluation of parameters until it is called. Since its never called, it is never evaluated
  printFirstParam(1, infinity())
}
