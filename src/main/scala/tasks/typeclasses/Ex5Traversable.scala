package u04lab
import u03.Sequences.*
import Sequence.*
import u03.Optionals.*
import Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: "+a)

  /*def logAll[A](seq: Sequence[A]): Unit = seq match
    case Cons(h, t) => log(h); logAll(t)
    case _ => ()
  */
  def logAll[T[_]: Traversable, A](ta: T[A]): Unit =
    val traversable = summon[Traversable[T]]
    traversable.foreach(ta)(log)

  trait Traversable[T[_]]:
    def foreach[A](t: T[A])(f: A => Unit): Unit

  given Traversable[Sequence] with
    override def foreach[A](seq: Sequence[A])(f: A => Unit): Unit = seq match
      case Cons(h, t) => f(h); foreach(t)(f)
      case _ => ()

  given Traversable[Optional] with
    override def foreach[A](opt: Optional[A])(f: A => Unit): Unit = opt match
      case Just(v) => f(v)
      case _ => ()


@main def tryTraversable =
  val nums: Sequence[Int] = Cons(1, Cons(2, Cons(3, Nil())))
  val optNum: Optional[Int] = Just(42)

  import Ex5Traversable.*

  logAll(nums)
  logAll(optNum)

