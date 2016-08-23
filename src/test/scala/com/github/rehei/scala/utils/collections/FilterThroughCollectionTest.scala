package com.github.rehei.scala.utils.collections

import java.util.ArrayList

import org.junit.Test

import com.github.rehei.scala.utils.collections.model.Element

class FilterThroughCollectionTest {

  val DEFAULT_CATEGORY = 2

  @Test
  def test() {

    val element1 = Element("1", DEFAULT_CATEGORY)
    val element2 = Element("2", DEFAULT_CATEGORY)
    val element3 = Element("3", DEFAULT_CATEGORY + 2)

    val base = new ArrayList[Element]()
    base.add(element1)
    base.add(element2)
    base.add(element3)

    val elementMatchingCategory = Element("matching category", DEFAULT_CATEGORY)
    val elementNotMatchingCategory = Element("not matching category", DEFAULT_CATEGORY + 1)

    val list = CollectionFactory(base).filterThrough(_.category == DEFAULT_CATEGORY)

    list.add(elementMatchingCategory)
    list.add(elementNotMatchingCategory)

    assert(list.size() == 3)
    assert(list.contains(element1) == true)
    assert(list.contains(element2) == true)
    assert(list.contains(element3) == false)
    assert(list.contains(elementMatchingCategory) == true)
    assert(list.contains(elementNotMatchingCategory) == false)

  }

}