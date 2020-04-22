package lectures.part4pm

object PatternsEverywhere extends App {

  // big idea #1
  try {
    //code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => npe
    case _ => "something good?"
  }


  // catches are actually matches!


  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x%2 ==0
  } yield 10 * x

  // generators are also based on pattern matching

  val tuples = List((1,2), (3,4))
  val filterTuples = for{
    (first, second) <- tuples
  } yield first * second

  // big idea #3
  val tuple = (1,2,3)
  val (a,b,c) = tuple
  println(c)
  // multiple value definitions based on Pattern Matching

  // Big idea 4 Partial functions
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "one"
    case _ => "something else"
  } //  partial function literal

  // SAME AS
  val mappedList2 = list.map { x => x match {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "one"
    case _ => "something else"
    }
  }
 // will put all members of the list through the match!
  println(mappedList)
}