package com.github.rehei.scala.utils.collections

import scala.reflect.ClassTag

case class CollectionFactory[T](base: java.util.Collection[T])(implicit classTag: ClassTag[T])
    extends AbstractCollection[T] {

  def add(element: T): Boolean = base.add(element)
  def addAll(elements: java.util.Collection[_ <: T]): Boolean = base.addAll(elements)
  def clear(): Unit = base.clear()
  def contains(e: Any): Boolean = base.contains(e)
  def containsAll(elements: java.util.Collection[_]): Boolean = base.containsAll(elements)
  def isEmpty(): Boolean = base.isEmpty()
  def iterator(): java.util.Iterator[T] = base.iterator()
  def remove(e: Any): Boolean = base.remove(e)
  def removeAll(elements: java.util.Collection[_]): Boolean = base.removeAll(elements)
  def retainAll(elements: java.util.Collection[_]): Boolean = base.retainAll(elements)
  def size(): Int = base.size()
  def toArray[T](e: Array[T with Object]): Array[T with Object] = base.toArray[T](e)
  def toArray(): Array[Object] = base.toArray()

}