package lectures.part2oop // this means that whatever we write in this nice object will be part of this package

import playground.ExampleForImports // this was imported. you can use "import playground._" instead to import EVERYTHING else ".{n1, n2, ...}"
/* You can also import with an alias " playground.{ExampleForImports => Alias} so now "new Alias" instead of "new ExampleForImports"
This is specially useful if you are using many classes with the same name but from different packages... else you have to use its full name
 */


object PackagingAndImports extends App {

  val writer = new Writer("Bruno", "Cervantes", 2020) // no need to import class Writer from OOBasics.... because its from our same package
  // Otherwise you need to import the Package
  val example = new ExampleForImports
  /*alternatively if you don't wish to add the import ... you do this:
  val example = new playground.ExampleForImports // fully qualified name
   */

  // package object // makes stuff defined inside the package object visible in the entire package
  // Very rarely used in practice
  sayHello
  println(SPEED_OF_LIGHT)

  /* default imports:
  java.lang - String, Object, Exception, etc
  scala - int, Nothing ......
  etc
   */



}
