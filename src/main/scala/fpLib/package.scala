package object fpLib {


  //point free implicit...
  implicit final class EnablePointFree[A,B](private val ab:A=>B) extends AnyVal {
    @inline def `-->`[C](bc: B=>C):A=>C = PointFree.compose(ab,bc)

  }

  implicit final class EnablePointFreeFunctionally[A,B,D[_]](private val ab:A=>D[B]) extends AnyVal {

      @inline def `>=>`[C](bc: B=>D[C])(implicit M:Monad[D]):A=>D[C] =
        PointFree.composeIO(ab,bc)


  }
}
