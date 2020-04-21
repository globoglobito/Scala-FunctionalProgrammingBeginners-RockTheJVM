package playground

import scala.annotation.tailrec

object ScalaPlayground extends App {

  def fun2(n:Int): Boolean = {
    val maxt = math.sqrt(n.abs)
    def fun2h(t: Int): Boolean = {
      if (n % t == 0) false
      else if(t > maxt) true
      else fun2h(t+1)
    }
    if(n==0) false
    else if(n.abs<=2) true
    else fun2h(2)
  }


  println(fun2(2))

}
