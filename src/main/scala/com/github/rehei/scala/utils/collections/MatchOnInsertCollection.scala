package com.github.rehei.scala.utils.collections

import scala.reflect.ClassTag

class MatchOnInsertCollection[T](
  protected val base: java.util.Collection[T],
  protected val matchOnInsertFunc: (T) => Boolean)(implicit classTag: ClassTag[T])
    extends AbstractCollection[T] {

  def add(x$1: T): Boolean = ???
  def addAll(x$1: java.util.Collection[_ <: T]): Boolean = ???
  def clear(): Unit = ???
  def contains(x$1: Any): Boolean = ???
  def containsAll(x$1: java.util.Collection[_]): Boolean = ???
  def isEmpty(): Boolean = ???
  def iterator(): java.util.Iterator[T] = ???
  def remove(x$1: Any): Boolean = ???
  def removeAll(x$1: java.util.Collection[_]): Boolean = ???
  def retainAll(x$1: java.util.Collection[_]): Boolean = ???
  def size(): Int = ???
  def toArray[T](x$1: Array[T with Object]): Array[T with Object] = ???
  def toArray(): Array[Object] = ???

}