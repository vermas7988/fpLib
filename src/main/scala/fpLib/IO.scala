package fpLib


final case class IO[+A](unsafeRun: () => A)  extends AnyVal
object IO {

    def create[A](a : =>A):IO[A] = IO(() => a)
    
    //monad impl
    implicit val M: Monad[IO] = new Monad[IO] {
        override def flatMap[A, B](ac: IO[A])
                                  (acb: A => IO[B]):
        IO[B] = IO.create {
            val a = ac.unsafeRun()
            acb(a).unsafeRun()
        }
    }
}