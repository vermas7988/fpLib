package object fpLib {


  //point free implicit...
  implicit final class EnablePointFree[A,B](private val ab:A=>B) extends AnyVal {
    @inline def `-->`[C](bc: B=>C):A=>C = PointFree.compose(ab,bc)

  }

  implicit final class EnablePointFreeFunctionally[A,B,D[_]](private val ab:A=>D[B]) extends AnyVal {
      @inline def `>=>`[C](bc: B=>D[C])(implicit M:Monad[D]):A=>D[C] =
        PointFree.composeIO(ab,bc)
  }


  /** This class helps to convert a given C[_] to desired HKT e.g. Monad[_] implicitly.*/
  implicit final class EnableHigherKindedTypes[C[_],A](private val ac: C[A]) extends AnyVal {

    @ inline def `>>=`[B](acb:A=>C[B])(implicit M:Monad[C]): C[B] = M.flatMap(ac)(acb)

    @ inline def flatMap[B](acb:A=>C[B])(implicit M:Monad[C]): C[B] = M.flatMap(ac)(acb)

    @ inline def map[B](acb:A=>B)(implicit M:Functor[C]): C[B] = M.map(ac)(acb)

    @ inline def flatten(value:C[C[A]])(implicit M:Monad[C]):C[A] = M.flatten(value)

  }

  /*implicit final class IOPimp[A](private val a:IO[A]) extends AnyVal {

    /**repeats a task forever/infinitely until some error occurs*/
    @ inline def forever = IO.forever(a)

    /**repeats a task until 'n' times or some error occurs*/
    @ inline def repeatN(n:Int) = IO.repeatN(a)(n)

    /**use this instead of unsafeRun() to handle error case also.*/
    @inline def safeRun() = IO.safeRun(a)
  }*/
}
