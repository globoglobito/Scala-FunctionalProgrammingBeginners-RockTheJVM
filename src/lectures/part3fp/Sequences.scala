package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  /*
  A very general interface for data structures that:
    Have a well defined order
    can be indexed
  Support various operations:
    apply, iterator, length, reverse for indexing and iterating
    concatenation, appending, prepending
    a lot of others: grouping, sorting, zipping, searching, slicing

   */

  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // print element in position 2, so 3rd
  println(aSequence ++ Seq(7, 5, 6))
  println(aSequence.sorted)


  // Ranges

  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("hello"))

  /* lists
   A LinearSeq immutable linked list
    head, tail, isEmpty methods are fast: O(1)
    most operations are O(n): length, reverse
   Sealed - has two subtypes
    object Nil (empty)
    class ::
   */

  val aList = List(1, 2, 3)
  val prependedAppended = 33 +: aList :+ 89 // +: to prepend, :+ to append
  println(prependedAppended)

  val apples5 = List.fill(5)("apple") // create a list with element apple 5 times
  println(apples5)
  println(aList.mkString("-|-"))

  /* arrays
  The equivalent of simple Java arrays
    can be manually constructed with predefined length
    can be mutated
    are interoperable with Java's T[] arrays
    indexing is fast
   */

  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[String](3) // instantiates a 3 element array of type string (sinc eits empty its null, null null
  threeElements.foreach(println) //case n point

  //mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  //arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion from array to a sequence. This is why an array can be operated as if it where a sequence
  println(numbersSeq) // its type sequence[Int] now


  /* Vector
  The default implementation for immutable sequences
  effectively constant indexed read and writes
  fast element addition
  implemented as a fixed branched trie
  good performance for large sizes
   */

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTimes(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield{
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  val numberArray = (1 to maxCapacity).toArray

  // list: keeps references to tail
  // updating an element in the middle takes long
  println(getWriteTimes(numbersList)) //slowest

  // vector depth of the three is small
  // needs to replace an entire 32-element chunk
  println(getWriteTimes(numbersVector)) //fastest


  // array
  println(getWriteTimes(numberArray)) // middle



}


