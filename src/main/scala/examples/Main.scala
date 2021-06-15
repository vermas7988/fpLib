package examples

import fpLib._

object Main {

  lazy val run =  {
    fun1 >=> fun2 //functional composition
  }
	

	lazy val fun1: Any => IO[String] = _ => IO.create{
		println("hello buddy! Can I know your name?")
		val name = scala.io.StdIn.readLine()
		name
	}

	lazy val fun2: String => IO[Unit] = a => IO.create{
		println(s"how are you ${a}")
	}




}