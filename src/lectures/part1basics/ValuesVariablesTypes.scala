package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x: Int = 420
  println(x)
  // VALS ARE IMMUTABLE
  val y = 111
  // Compiler can infer types

  val aString: String = "hello"

  val aBoolean: Boolean = false
  val aChar: Char = 'x'
  val aShort: Short = 4564
  val aLong: Long = 5162354896L
  val aFloat: Float = 2.8f
  val aDouble: Double = 3.14


  // variables

  var aVariable: Int = 4
  aVariable = 5

}
