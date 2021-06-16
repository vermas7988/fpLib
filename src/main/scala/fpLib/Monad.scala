package fpLib

trait Monad[C[_]] extends Applicative[C] {

  //since we know there will be flatmap already.. lets try implementing flatmap using map
  //  def flatMap[A,B](ac:C[A])(acb:A=>C[B]):C[B]
  def flatMap[A,B](ac:C[A])(acb:A=>C[B]):C[B] = flatten(map(ac)(acb))
  @inline def >>=[A,B](ac:C[A])(acb:A=>C[B]):C[B] = flatMap(ac)(acb) //lambda function (haskell)

  def flatten[B](value: C[C[B]]):C[B]

}

object Monad extends Summoner[Monad]