package com.thoughtworks.DDF.Combinators

import com.thoughtworks.DDF.Arrow.Arrow

trait Let[Info[_], Repr[_]] extends Arrow[Info, Repr] {
  def Let[A, B](implicit ai: Info[A], bi: Info[B]): Repr[A => (A => B) => B]

  final def Let_[A, B](a: Repr[A])(implicit bi: Info[B]): Repr[(A => B) => B] = app(Let(reprInfo(a), bi))(a)

  final def Let__[A, B]: Repr[A] => Repr[(A => B)] => Repr[B] = a => f =>
    app(Let_(a)(arrowRangeInfo(reprInfo(f))))(f)
}
