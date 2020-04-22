package lectures.part4pm

import exercises.{EmptyList, MyList, NonEmpty}

object AllThePatterns extends App {

  // 1) Constants:
  val x: Any = "Potato"
  val constants = x match {
    case 1 => "number 1"
    case Numeric => "A number that is not 1"
    case "Potato" => "A Potato"
    case true => "True"
    case AllThePatterns => "A singleton object"
  }

  // 2) match anything

  // wildcard
  val matchAny = x match {
    case _ =>
  }

  //  variable
  val matchAVariable = x match {
    case variable => s"I've found a $variable"
  }

  // 3) tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) =>
    case (something,2) => s" first value of tuple is $something"
  }
  val nestedTuple = (1, (2, 3))
  val matchNested = nestedTuple match {
    case (_ ,(2, v) ) =>
  }


  // 4) case classes  - constructor pattern
  // Pattern matching can be nested with CCs as well
  val aList: MyList[Int] = NonEmpty(1, NonEmpty(2, EmptyList))
  val matchAlist = aList match {
    case EmptyList =>
    case NonEmpty(head, NonEmpty(subhead, subtail)) =>

  }

  // 5) list patterns
  val aStandardList = List(1,2,3,43)
  val standardListMatching = aStandardList match {
    case List(1,_,_,_) => // extractor  (advanced)
    case List(1,_*) => // list of arbitrary length (advanced)
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 43 => // infix pattern
    case _ =>
  }

  // 6) type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match{
    case list: List[Int] => "a list" // explicit type specifier
    case _ =>
  }

  // 7) name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ NonEmpty(_,_) => // name binding => use the name later (here)
    case NonEmpty(1, rest @ NonEmpty(2,_)) => //name binding nested patterns
    case _ =>

  }

  // 8) multi-patterns
  val multipattern = aList match {
    case EmptyList | NonEmpty(0,_) => // compound pattern matching
    case _ =>

  }

  // 9 if guards
  val secondElementSpecial = aList match {
    case NonEmpty(_, NonEmpty(specialElement, _)) if specialElement % 2 == 0 =>
    case _ =>
  }

// ALL

//EX: What will this match return?
  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => " a list of strings"
    case listOfNumbers: List[Int] => " a list of numbers"
    case _ => ""
  }

  println(numbersMatch)

  /* Answer a list of strings????? why????
   JVM backwards compatibility doest take Generics (basically oblivious after type checking). This only occurs of you have multiple List or any Generic
   If you only have one (as per below) the warning wont appear and it will work as expected
   This is named TYPE ERASURE
   */

  val numbersMatch2 = numbers match {
    case listOfStrings: List[Int] => " a list of numbers"
    case _ => ""
  }
  println(numbersMatch2)







}
