package fpLib


final case class IO[+A](unsafeRun: () => A)  extends AnyVal
object IO {

    def create[A](a : =>A):IO[A] = IO(() => a)
    
    //monad impl
    implicit val M: Monad[IO] = new Monad[IO] {
        /*final override def flatMap[A, B](ac: IO[A])
                                  (acb: A => IO[B]):
        IO[B] = IO.create {
            val a = ac.unsafeRun()
            acb(a).unsafeRun()
        }*/

      final override def flatten[B](value: IO[IO[B]]):IO[B] = IO.create{
        value.unsafeRun().unsafeRun()
      }

      final override def pure[A](a: A): IO[A] = IO.create(a)


      final override def map[A, B](ac: IO[A])(ab: A => B): IO[B] = IO.create{
        val a = ac.unsafeRun()
        val b = ab(a)
        b
      }
      
    }
}