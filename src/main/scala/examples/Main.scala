package examples

import fpLib._

object Main {

	val M: Monad[IO] = Monad[IO]

  def run: IO[Unit] =  {
//		M.>>=(fun1){ a =>     //way 1
//			fun2(a)
//		}
		 //map
	//	fun1.`>>=`(fun2)      //flatmap way 2
		for {
			//below one prints 2 infinitely..
			a <- IO.forever(IO.create(2).map(a=>println(a)))
			f1 <- fun1
			f2 <- fun2(f1)
		}    yield ()
  }
	

	val fun1: IO[String] = IO.create{
		println("hello buddy! Can I know your name?")
		val name = scala.io.StdIn.readLine()
		name
	}

	def fun2(a:String): IO[Unit] = IO.create{
		println(s"how are you ${a}")
	}




}