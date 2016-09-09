package com.github.rehei.scala.utils.collections

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.reflect.ClassTag

class MapThroughCollection[T, B](
  protected val base: java.util.Collection[T],
  protected val mapFunc: (T) => (B),
  protected val mapFuncReverseOption: Option[(B) => (T)])(implicit classTag: ClassTag[B])
    extends AbstractCollection[B] {

  def mapReverse(func: (B) => (T)) : AbstractCollection[B] = {
    new MapThroughCollection(base, mapFunc, Some(func))
  }
  
  override def add(element: B) = {
    base.add(mapFuncReverse(element))
  }

  override def addAll(elements: java.util.Collection[_ <: B]): Boolean = {
    for (element <- elements) {
      this.add(element)
    }
    true
  }

  override def clear(): Unit = {
    base.clear()
  }

  override def containsAll(elements: java.util.Collection[_]): Boolean = {
    mappedBaseCollection.containsAll(elements)
  }
  override def contains(delegateElement: Any): Boolean = {
    mappedBaseCollection.contains(delegateElement)
  }

  override def isEmpty(): Boolean = {
    mappedBaseCollection.isEmpty
  }

  override def iterator(): java.util.Iterator[B] = {
    mappedBaseCollection.iterator
  }

  override def size(): Int = {
    mappedBaseCollection.size
  }

  override def removeAll(elements: java.util.Collection[_]): Boolean = {
    var removedAll = true
    for (element <- elements) {
      removedAll = removedAll && this.remove(element)
    }
    removedAll
  }

  override def remove(element: Any): Boolean = {
    base.remove(mapFuncReverse(element.asInstanceOf[B]))
  }

  override def retainAll(elements: java.util.Collection[_]): Boolean = {
    val toBeRemoved = base.filter(m => elements.contains(mapFuncReverse(m.asInstanceOf[B])))
    this.removeAll(toBeRemoved)
  }

  override def toArray[B](arg: Array[B with Object]): Array[B with Object] = {
    mappedBaseCollection.asJavaCollection.toArray[B](arg)
  }
  override def toArray(): Array[Object] = {
    mappedBaseCollection.asJavaCollection.toArray(new Array[Object](0))
  }

  def mappedBaseCollection = base.map(mapFunc)

  def mapFuncReverse(element: B): T = {
    mapFuncReverseOption.map(m => m apply element).get
  }

}