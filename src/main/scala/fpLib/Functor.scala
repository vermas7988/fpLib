package fpLib

trait Functor[C[_]] {

  //  def lift[A,B]: (A=>B) =>( C[A]=>C[B] )  //other definition of map
  //  (it basically lifts the function to higher kinds)
  def map[A,B](ac: C[A])(ab: A=>B):C[B]

}
object Functor extends Summoner[Functor]

