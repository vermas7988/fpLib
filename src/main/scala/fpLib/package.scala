package object fpLib {


  //point free implicit...
  implicit final class EnablePointFree[A,B](private val ab:A=>B) extends AnyVal {
    @inline def `-->`[C](bc: B=>C):A=>C = PointFree.compose(ab,bc)

  }

  implicit final class EnablePointFreeFunctionally[A,B,D[_]](private val ab:A=>D[B]) extends AnyVal {
      @inline def `>=>`[C](bc: B=>D[C])(implicit M:Monad[D]):A=>D[C] =
        PointFree.composeIO(ab,bc)
  }

  implicit final class EnableHigherKindedTypes[C[_],A](private val ac: C[A]) extends AnyVal {
    @ inline def `>>=`[B](acb:A=>C[B])(implicit M:Monad[C])  = M.flatMap(ac)(acb)
    @ inline def flatMap[B](acb:A=>C[B])(implicit M:Monad[C])  = M.flatMap(ac)(acb)

    @ inline def map[B](acb:A=>B)(implicit M:Monad[C])  = M.map(ac)(acb)

  }
}
