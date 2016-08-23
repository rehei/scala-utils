package com.github.rehei.scala.utils.collections

import scala.reflect.ClassTag

abstract class AbstractCollection[T](implicit val classTag: ClassTag[T]) extends java.util.Collection[T] {

  def filterThrough(filterFunc: (T) => Boolean) = {
    new FilterThroughCollection(this, filterFunc)
  }

  def matchOnInsert(filterFunc: (T) => Boolean) = {
    new MatchOnInsertCollection(this, filterFunc)
  }

  def modifyOnInsert(modification: (T) => Unit) = {
    new ModifyOnInsertCollection(this, modification)
  }

}