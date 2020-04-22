package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // No shame in using Try-Catch block, but its a pain in the arse if you need to catch several different. This is an alternative
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("FAILUUUUURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("YOU DIED")


  // Try objects via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure) // instead of blowing an exception in your face  and crashing. Try tried to catch your failure and wrap it into a Failure if caught

  // syntax sugar just like regular 'try'
  val anotherPotentialFailure = Try {
    unsafeMethod()
  }
  println(anotherPotentialFailure)

  // utilities
  println(potentialFailure.isSuccess)

  //orElse aka how to handle unsafe API (that throws exceptions)
  def backupMethod(): String = "Primary method failed... at least no exception amirite?"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))

  // If you had to design that API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("Weh"))
  def betterBackupMethod(): Try[String] = Success("It failed... but no exceptions!")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  //map, flatMap, filter

  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x, x+1)))
  println(aSuccess.filter(_ > 10)) // will throw a failure


  //EX: create a process that uses the APi provided (class and object) to run renderHTML
  val hostname = "localhost"
  val port ="8080"
  def renderHTML(page :String) = println(page)

  //API
  class Connection {
    def get(urls: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted")
    }
    def getSafe(url: String): Try[String] = Try(get(url)) // if this didn't exist you'd have to create this method outside
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port:String): Connection =
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port:String): Try[Connection] = Try(getConnection(host,port)) // if this didn't exist you'd have to create this method outside

  }
// THe nice thing of this logic is that you are able to handle uncaught exceptions
  for {
    connection <- HttpService.getSafeConnection(hostname,port)
    html <- connection.getSafe("/home")
  } renderHTML(html)


}
