package lectures.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)


  //map: One to one
  println(list.map(_+1))
  println(list.map(_+ "is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap: One to many
  val toPair = (x:Int) => List(x, x+1) // lambda function
  println(list.flatMap(toPair))

  //print all combinations between 2 lists
  val numbers = List(1,2,3)
  val characters = List('a', 'b', 'c')
  val colour = List("Red", "Black", "Blue")

  // 2 lists to combine
  val combined = numbers.flatMap(n => characters.map(c => s"$c" + n ))
  println(combined)

  //3 lists to combine
  val allCombined = numbers.filter(_ % 2 ==0)flatMap(n => characters.flatMap(c => colour.map(x => s"$x" + n + c )))
  println(allCombined)

  // functional of "iterating"


  //foreach
  list.foreach(println)


  // for-comprehension aka how to make chains of flatMap and maps palatable

    val forCombination = for {
    n <- numbers if n % 2 ==0 /// Here we are adding a guard, which is basically a filter function
    c <- characters
    x <- colour
  } yield "" + x + n + c

  println(forCombination)

  for{
    n <- numbers
  } println(n) // functional sideffect

  //syntax overload





}
