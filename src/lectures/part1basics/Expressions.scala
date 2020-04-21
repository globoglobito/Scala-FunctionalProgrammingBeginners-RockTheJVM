package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // EXPRESSION

  println(x)
  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (this last one is right shift with zero extension)

  println(1 == x)
  // == != > >= <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // -= *= /=
  println(aVariable)

  // Instructions (DO something) vs Expressions (give me the VALUE of something)
  // Instructions are executed (Python, Java), expressions are evaluated (Scala)

  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPPRESSION NOT IF INSTRUCTION
  println(aConditionedValue)

  // NEVER DO IMPERATIVE CODE AKA WHILE LOOPS
  // EVERYTHING in Scala is an Expression

  val aWeirdValue = (aVariable = 3) // Unit == void
  println(aWeirdValue)

  // side efects: println(), whiles, reassigning

  // Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (y > 2) "hello" else "bye"
  } // the type of a block is the value of its last expression





}
