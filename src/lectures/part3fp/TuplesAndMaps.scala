package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered lists
  val aTuple = new Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // prints 2
  println(aTuple.copy(_2 = "wololo")) // prints a copy with element 2 being different
  println(aTuple.swap) // changes order ("Hello, Scala", 2)


  // Maps, key -> values pair

  val phonebook = Map(("Jim", 555), "Pepe" -> 666).withDefaultValue(-1) // a -> b is syntactic sugar. We use the .withdefaultMethod to prevent raising an exception if we attempt printing a value that doesnt exist.
  println(phonebook)

  //maps ops
  println(phonebook.contains("Pepe")) // true
  println(phonebook("Jimbo")) // returns the default value, otherwise it would throw an exception

  // add a new pairing
  val newPairing = "Mary" -> 986
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  //functions on maps:
  // map, flatmaps, filters
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))
  //mapValues
  println(phonebook.mapValues(number => "Wololo-" + number))

  // conversions to other collections

  println(phonebook.toList)
  println(List(("Voltron", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // creates a map with key being the first letter, values being a list of names that have that letter at first position


  /*
What happens if you have a map with similar keys?
EX: JIM, 2000 and jim, 555
> You can add them to the same map as both are different enough.
 But if you decide to lower case them, then you will loose one of the key-value pair, in this case you only kept the 2nd value.
 */

  val phonebook2 = Map(("Jim", 555), "JIM" -> 666).withDefaultValue(-1)
  println(phonebook2.map(pair => pair._1.toLowerCase -> pair._2))


  /* Overly Simplified social network based on maps
Person = String
  add a person to the network
  remove
  friend (mutual)
  unfriend

  number of friends of a person
  person with most friends
  persons with no friends
  is there s social connection between 2 people?


 */

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }
  def remove(network: Map[String, Set[String]], person: String):  Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }



}

