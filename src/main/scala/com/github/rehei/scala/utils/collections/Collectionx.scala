package com.github.rehei.scala.utils.collections

class Collectionx(base: java.util.Collection[_]) {

  def bidiFilter() = {
    new BidiFilteredCollection(base)
  }

}