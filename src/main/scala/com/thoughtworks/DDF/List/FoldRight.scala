package com.thoughtworks.DDF.List

import com.thoughtworks.DDF.Product.Product

trait FoldRight[Info[_], Repr[_]] extends Product[Info, Repr] with ListMin[Info, Repr] {
  def foldRight[A, B](implicit ai: Info[A], bi: Info[B]): Repr[(A => B => B) => B => scala.List[A] => B]

  final def foldRight_[A, B]: Repr[A => B => B] => Repr[B => scala.List[A] => B] = f =>
    app(foldRight(arrowDomainInfo(reprInfo(f)), arrowRangeInfo(arrowRangeInfo(reprInfo(f)))))(f)

  final def foldRight__[A, B]: Repr[A => B => B] => Repr[B] => Repr[scala.List[A] => B] = f => app(foldRight_(f))

  final def foldRight___[A, B]: Repr[A => B => B] => Repr[B] => Repr[scala.List[A]] => Repr[B] = f => x =>
    app(foldRight__(f)(x))
}
