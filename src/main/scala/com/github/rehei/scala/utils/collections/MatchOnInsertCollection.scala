package com.github.rehei.scala.utils.collections

import scala.reflect.ClassTag
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class MatchOnInsertCollection[T](
  protected val base: java.util.Collection[T],
  protected val matchOnInsertFunc: (T) => Boolean)(implicit classTag: ClassTag[T])
    extends AbstractCollection[T] {

  def add(element: T): Boolean = {
    if (matchOnInsertFunc(element)) {
      base.add(element)
    } else {
      false
    }
  }
  def addAll(elements: java.util.Collection[_ <: T]): Boolean = {
    elements.foldLeft(false)((foldValue, element) => foldValue && base.add(element))
  }
  def clear(): Unit = {
    base.clear()
  }
  def contains(element: Any): Boolean = {
    base.contains(element)
  }
  def containsAll(elements: java.util.Collection[_]): Boolean = {
    base.containsAll(elements)
  }
  def isEmpty(): Boolean = {
    base.isEmpty()
  }
  def iterator(): java.util.Iterator[T] = {
    base.iterator()
  }
  def remove(element: Any): Boolean = {
    base.remove(element)
  }
  def removeAll(elements: java.util.Collection[_]): Boolean = {
    base.removeAll(elements)
  }
  def retainAll(elements: java.util.Collection[_]): Boolean = {
    base.retainAll(elements)
  }
  def size(): Int = {
    base.size()
  }
  def toArray[T](element: Array[T with Object]): Array[T with Object] = {
    base.toArray[T](element)
  }
  def toArray(): Array[Object] = {
    base.toArray()
  }

}