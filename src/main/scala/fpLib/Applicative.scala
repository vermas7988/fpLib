package fpLib

trait Applicative[C[_]] extends Functor[C] {

  def pure[A](a:A):C[A]
  @inline def unit[A](a:A):C[A]  = pure(a)

}
object Applicative extends Summoner[Applicative]

