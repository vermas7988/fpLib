package fpLib

object PointFree {

  def compose[A, B, C](ab: A => B, bc: B => C): A => C = {
    a => {
      val b = ab(a)
      val c = bc(b)
      c
    }
  }

  def composeIO[A,B,C,D[_]:Monad](afb: A => D[B], bfc: B => D[C]): A => D[C] = a => {
    val db = afb.apply(a)
    val dc = Monad[D].>>=(db)(bfc)
    dc
  }


}