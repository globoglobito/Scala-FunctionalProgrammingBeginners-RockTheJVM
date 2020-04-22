package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // Use 1: switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "It's a one"
    case 2 => "Double trouble"
    case 3 => "Threeeeeeee"
    case _ => "The rest" // _ is a wildcard for everything else not defined previously
  }

  println(x)
  println(description)


  // Use 2 Decompose Values
  case class Person(name: String, age: Int)
  val bob = Person("bob", 20 )

  val greeting = bob match {
    case Person(n,a) if a < 21 => s"I am $n and i cant drink in Murica" // with a guard
    case Person(n,a) => s"I am $n and I am $a years old"
    case _ => "who am I"  // without this guy if you pass something else an exception would be raised
  }

  println(greeting)
  /*
  Cases are matched in order
  If no case match you get a MatchError exception
  The type of of the PM expression would reflect the possible cases
  PM work really well with case classes

  PM is not a do all make all, sometimes simplicity is better!
   */

  /* EX:
  Make a simple function that uses PM
  Transform expressions into Human readable form
  EX: Sum(number(3), number(4) = 3 + 4
      Prod(Sum(number(2), number(2)), number(4)) == (2+2) * 4
   */

  trait Expr
  case class Number(n: Int) extends  Expr
  case class Sum(e1: Expr, e2: Expr) extends  Expr
  case class Prod(e1: Expr, e2: Expr) extends  Expr

  // Answer:

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(n1,n2) => show(n1) +" + "+ show(n2)
    case Prod(n1, n2) => {
      def MaybeShow(exp: Expr) = exp match {
        case Prod(_,_) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      MaybeShow(n1) + "*" +  MaybeShow(n2)
    }
  }

  println(show(Sum(Number(3), Number(4))))
  println(show(Sum(Prod(Number(2), Number(2)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(2)), Number(4))))



}
