package lectures.part3fp

object AnonymousFunctions extends App {

  // Rather than having to override def apply like in the previous lecture, we can simply do for doubler:

  val doubler: Int => Int = (x: Int) => x * 2 // An anonymous function. it instantiates a new function1 with x and returns x * 2 aka LAMBDA FUNCTIONS

  // multiple params in lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // No params
  val justDo: () => Int = () => 3

  // careful
  println(justDo) // this will print out the instance
  println(justDo()) // this will actually print the 3
  // Unlike normal functions YOU NEED to specify the empty () with lambdas

  // curly braces with lambdas (style)
  val stringtoInt = { (str: String) =>
    str.toInt
  }


  // MOAR syntactic sugar

  val niceIncrementer: Int => Int = _ + 1 // equivalent to (x: Int) => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (x: Int, y: Int) => x + y

  // rewrite super adder to make it lambda:
 //val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
 //  override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
 //    override def apply(y: Int): Int = x + y
 //  }

  val superAdder = (x: Int) => (y: Int) => x + y
  val superAdder2: Int => Int => Int = x => y => x + y
  // Pick your poison both mean the same















}
