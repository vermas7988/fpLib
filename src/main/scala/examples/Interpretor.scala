package examples

import fpLib._

object Interpretor extends App {

  lazy val description = Main.run

  print(Console.CYAN)
  println("\u2500"*100)
  interpret(description)
  println("\u2500"*100)
  print(Console.RESET)



  def interpret[A](description: IO[A]):A = {
    description.unsafeRun()
  }

  // def interpret[A](description: A):A = {
  //   description  //()=>A
  // }

    
}


