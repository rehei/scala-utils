package com.github.rehei.scala.utils.collections

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.reflect.ClassTag

class ModifyOnInsertCollection[T](
  protected val base: java.util.Collection[T],
  protected val modifyOnAddFuncUnit: (T) => Unit)(implicit classTag: ClassTag[T])
    extends AbstractCollection[T] {

  protected val modifyOnAddFuncIdentity = (model: T) => {
    modifyOnAddFuncUnit(model)
    model
  }

  override def add(element: T) = {
    base.add(modifyOnAddFuncIdentity(element))
  }

  override def addAll(elements: java.util.Collection[_ <: T]): Boolean = {
    base.addAll(elements.map(modifyOnAddFuncIdentity))
  }

  override def clear(): Unit = {
    base.clear()
  }

  override def retainAll(elements: java.util.Collection[_]): Boolean = {
    base.retainAll(elements)
  }

  override def containsAll(elements: java.util.Collection[_]): Boolean = {
    base.containsAll(elements)
  }

  override def contains(delegateElement: Any): Boolean = {
    base.contains(delegateElement)
  }

  override def isEmpty(): Boolean = {
    base.isEmpty
  }

  override def iterator(): java.util.Iterator[T] = {
    base.iterator
  }

  override def removeAll(elements: java.util.Collection[_]): Boolean = {
    base.removeAll(elements)
  }

  override def remove(element: Any): Boolean = {
    base.remove(element)
  }

  override def size(): Int = {
    base.size()
  }

  override def toArray[B](arg: Array[B with Object]): Array[B with Object] = {
    base.toArray[B](arg)
  }
  override def toArray(): Array[Object] = {
    base.toArray(new Array[Object](0))
  }

}