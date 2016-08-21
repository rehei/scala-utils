package com.github.rehei.scala.utils.collections

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.reflect.ClassTag
import scala.reflect.{ ClassTag, classTag }

class BidiFilteredCollection[T](
    private val baseInternal: java.util.Collection[T],
    private val filterFunc: (T) => Boolean,
    private val modifyOnAddFuncUnit: (T) => Unit,
    private val checkOnAddFunc: (T) => Boolean)(implicit val classTag: ClassTag[T]) extends java.util.Collection[T] {

  def this(baseInternal: java.util.Collection[T])(implicit classTag: ClassTag[T]) = {
    this(baseInternal,
      (_ => true),
      (_ => Unit),
      (_ => true))
  }

  val modifyOnAddFuncIdentity = (model: T) => {
    modifyOnAddFuncUnit(model)
    model
  }

  def where(filter: (T) => Boolean) = {
    new BidiFilteredCollection(baseInternal, filter, modifyOnAddFuncUnit, checkOnAddFunc)
  }

  def addingObjectWithModification(modification: (T) => Unit) = {
    new BidiFilteredCollection(baseInternal, filterFunc, modification, checkOnAddFunc)
  }

  def addingObjectMustSatisfy(check: (T) => Boolean) = {
    new BidiFilteredCollection(baseInternal, filterFunc, modifyOnAddFuncUnit, check)
  }

  protected def baseCollection = {
    baseInternal.filter(filterFunc)
  }

  override def add(element: T) = {
    if (checkOnAddFunc(element)) {
      modifyOnAddFuncUnit(element)
      baseInternal.add(element)
    } else {
      false
    }
  }

  override def addAll(elements: java.util.Collection[_ <: T]): Boolean = {
    def checkedAndModifiedElementCollection = {
      elements
        .filter(checkOnAddFunc)
        .map(modifyOnAddFuncIdentity)
    }
    baseInternal.addAll(checkedAndModifiedElementCollection)
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