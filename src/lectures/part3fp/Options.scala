package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  // Option was invented to deal with unsafe APIs
  def unsafeMethod(): String = null // expects strings but might actually return a null
  //val result = Some(null) // Wrong Dont do this, you dont need to do the null check
  val result = Option(unsafeMethod()) // Option does the Some or None check for you

  //chained methods
  def backupMethods(): String = "A Valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethods())) // basically if null is passed by the first method you immediately use the 2nd method

  // Design Unsafe Apis
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("a valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Option
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE DO NOT USE

  // map, flatMap, filter

  println(myFirstOption.map(_*2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap( x => Option(x*9)))




  //EX:

  val config: Map[String, String] = Map(
    //fetched from somewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection{
    def connect = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }
  //try to establish connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection(h,p)))
  /* equivalent imperative code.
  if (h != null)
    if (p != null)
      return Connection.Apply(h,p)
  else return null
   */
  val connectionStatus = connection.map(c => c.connect)
  /* if (c != null)
        return c.connect
      else  return null
   */
  println(connectionStatus)
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host,port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions -- nicest way imho

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)


}
