package fpLib

trait Monad[C[_]] {
  def flatMap[A,B](ac:C[A])(acb:A=>C[B]):C[B]
  @inline def >>=[A,B](ac:C[A])(acb:A=>C[B]):C[B] = flatMap(ac)(acb) //lambda function (haskell)

  def map[A,B](ac: C[A])(ab: A=>B):C[B]
}
object Monad {
  def apply[C[_]:Monad]:Monad[C] = implicitly[Monad[C]]
}