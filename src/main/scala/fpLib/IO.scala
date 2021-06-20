package fpLib

import scala.util.Try


final case class IO[+A](unsafeRun: () => A)  extends AnyVal
object IO {

  /** Default create method// wrap method implementation into IO.create{}
   * and use the fp features that IO is providing.*/
  def create[A](a : =>A):IO[A] = IO(() => a)

  /** Comes with an either.. so that we have to handle errors anyways
   * .......can be improved.*/
  def createSafely [A](a: IO[A]) = IO.create{
    Try(a.unsafeRun()).toEither
  }

  /**use this instead of unsafeRun() to handle error case also.*/
  def safeRun[A](a: IO[A]) = {
    Try(a.unsafeRun()).toEither
  }

  /**repeats a task forever/infinitely until some error occurs*/
  def forever[A](a: IO[A]) =  IO.create{
    while (true){
      a.unsafeRun()
    }
  }

  /**repeats a task until 'n' times or some error occurs*/
  def repeatN[A](a: IO[A])(n:Int) =  IO.create{
    var t = n
    while (t>0){
      a.unsafeRun()
      t-=1
    }
  }
    
    /**All of the monad features also........ Impl of Monad typeclass*/
    implicit val M: Monad[IO] = new Monad[IO] {
        /*final override def flatMap[A, B](ac: IO[A])
                                  (acb: A => IO[B]):
        IO[B] = IO.create {
            val a = ac.unsafeRun()
            acb(a).unsafeRun()
        }*/

      /**Flattens the IO[IO[_]] to IO[_] */
      final override def flatten[B](value: IO[IO[B]]):IO[B] = IO.create{
        value.unsafeRun().unsafeRun()
      }

      /**alias for create*/
      final override def pure[A](a: A): IO[A] = IO.create(a)


      /**maps a given function to the underlying IO value*/
      final override def map[A, B](ac: IO[A])(ab: A => B): IO[B] = IO.create{
        val a = ac.unsafeRun()
        val b = ab(a)
        b
      }
      
    }
}