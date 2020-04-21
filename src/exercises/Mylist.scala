package exercises

/*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is this list empty?
  add(int) => new list with this element added
  toString => a string representation of the list


  //NOTE  Mylist used to be only INT, it has been transformed to Generic type +A
  Why +A? because in doing so we can construct list of anytype including mixed types!
 */

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]  // REMEMBER B being any superclass of A
  def printElements: String
  override def toString: String = "[" + printElements + "]"

 // These are high order functions. functions that either receive functions as parameters or else returns functions as parameter

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean):MyList[A]
  //conactenation
  def ++[B >: A](list:MyList[B]): MyList[B]

  //hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A,A) => Int): MyList[A]
  def zipWith[B,C](list: MyList[B], zip:(A,B) => C) : MyList[C]
  def fold[B](start: B)(operator: (B,A) => B): B

}

case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing] (element: B): MyList[B] = new NonEmpty(element, EmptyList)
  override def printElements: String = ""

  def map[B](transformer: Nothing =>B): MyList[B] = EmptyList
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList
  def filter(predicate:Nothing => Boolean):MyList[Nothing] = EmptyList

  def ++[B >: Nothing](list:MyList[B]): MyList[B] = list

  //hofs
  def foreach(f: Nothing => Unit): Unit = () // does nothing return the unit value
  def sort(compare: (Nothing, Nothing) => Int) = EmptyList
  def zipWith[B,C](list: MyList[B], zip:(Nothing,B) => C) : MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else EmptyList
  }
  def fold[B](start: B)(operator: (B,Nothing) => B) : B = start


}

case class NonEmpty[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new NonEmpty(element, this)

    def printElements: String = {
      if (t.isEmpty) "" + h
      else h + " " + t.printElements
    }

  def filter(predicate: A => Boolean): MyList[A] = {
      if (predicate(h)) new NonEmpty(h, t.filter(predicate))
      else t.filter(predicate)
    }

  def map[B](transformer: A => B): MyList[B] = {
    new NonEmpty(transformer(h), t.map(transformer))

  }

  def ++[B >: A](list:MyList[B]): MyList[B] = new NonEmpty(h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  //hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }
  def sort(compare: (A,A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new NonEmpty(x, EmptyList)
      else if (compare(x,sortedList.head) <= 0) new NonEmpty(x, sortedList)
      else new NonEmpty(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }
  def zipWith[B,C](list: MyList[B], zip:(A,B) => C) : MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new NonEmpty(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start,h))(operator)
  }



}

///// consider this the condition that filter has to be true for it not to be filtered out
//trait MyPredicate[-T] { // Already basically a function type T=>Boolean
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A, B] { // A=>B
//  def transform(elem: A ): B
//} THIS part is no longer needed as we just refactored the abstract class to use function types from the get go



object ListTest extends App{
  val listOfIntegers: MyList[Int] = new NonEmpty(1, new NonEmpty(2,new NonEmpty(3, EmptyList)))
  val listOfStrings: MyList[String] = new NonEmpty("Hello", new NonEmpty("Wololo",new NonEmpty("Something", EmptyList)))
  val listOfEverything: MyList[Any] = new NonEmpty("Hello", new NonEmpty(20,new NonEmpty("Something", EmptyList)))
  val anotherListOfIntegers: MyList[Int] = new NonEmpty(4, new NonEmpty(5,new NonEmpty(6, EmptyList)))

 println(listOfIntegers)
 println(listOfStrings)
 println(listOfEverything)

 println(listOfIntegers.map((elem: Int) => elem * 2).toString) // with less syntactic sugar

// How map is working:

/*= new NonEmpty(2,[2,3].map(n*2))
  = new NonEmpty(2,new NonEmpty(4,[3].map(n*2)))
  = new NonEmpty(2,new NonEmpty(4,new NonEmpty(6,Empty.map(n*2))))
   = new NonEmpty(2,new NonEmpty(4,new NonEmpty(6,Empty))))


 */
  println(listOfIntegers.filter(_ % 2 == 0).toString) // with more sugar


// How flatmap is working:
/* [1,2,3].flatMap(n => [n, n+1])
   = [1,2] ++ [2,3].flatMap(n => [n, n+1])
   = [1,2] ++ [2,3] ++ [3].flatMap(n => [n, n+1])
   = [1,2] ++ [2,3] ++ [3,4] ++ Empty

 */
  println(listOfIntegers.flatMap((elem: Int) => new NonEmpty(elem, new NonEmpty(elem + 1, EmptyList))).toString) // cant use the _ becuase we are claling elem multiple times and _ is only once per param

  listOfIntegers.foreach(x => println(x))
  println(listOfIntegers.sort((x,y) => y-x))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  println(listOfIntegers.fold(10)(_ + _)) // its reduce!

  // for comprehension was already defined because of our implementation
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string

  println(combinations)
}