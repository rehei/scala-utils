package com.github.rehei.scala.utils.collections

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.reflect.ClassTag

class FilterThroughCollection[T](
  protected val baseInternal: java.util.Collection[T],
  protected val filterFunc: (T) => Boolean)(implicit classTag: ClassTag[T])
    extends AbstractCollection[T] {

  protected def baseCollection = {
    baseInternal.filter(filterFunc)
  }

  override def add(element: T) = {
    baseInternal.add(element)
  }

  override def addAll(elements: java.util.Collection[_ <: T]): Boolean = {
    baseInternal.addAll(elements)
  }

  override def clear(): Unit = {
    for (element <- baseCollection) {
      this.remove(element)
    }
  }

  override def retainAll(elements: java.util.Collection[_]): Boolean = {
    val toBeRemoved = baseCollection.filter(m => elements.contains(m))
    this.removeAll(toBeRemoved)
  }

  override def containsAll(elements: java.util.Collection[_]): Boolean = {
    baseCollection.containsAll(elements)
  }

  override def contains(delegateElement: Any): Boolean = {
    baseCollection.contains(delegateElement)
  }

  override def isEmpty(): Boolean = {
    baseCollection.isEmpty
  }

  override def iterator(): java.util.Iterator[T] = {
    baseCollection.iterator
  }

  override def removeAll(elements: java.util.Collection[_]): Boolean = {
    var removedAll = true
    for (element <- elements) {
      removedAll = removedAll && this.remove(element)
    }
    removedAll
  }

  override def remove(element: Any): Boolean = {
    baseInternal.remove(element)
  }

  override def size(): Int = {
    baseCollection.size
  }

  override def toArray[B](arg: Array[B with Object]): Array[B with Object] = {
    baseCollection.asJavaCollection.toArray[B](arg)
  }
  override def toArray(): Array[Object] = {
    baseCollection.asJavaCollection.toArray(new Array[Object](0))
  }

}