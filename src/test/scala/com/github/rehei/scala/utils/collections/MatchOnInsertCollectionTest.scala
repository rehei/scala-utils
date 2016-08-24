package com.github.rehei.scala.utils.collections

import java.util.ArrayList

import com.github.rehei.scala.utils.collections.model.Element
import org.specs2._
import org.specs2.mock.Mockito
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class MatchOnInsertCollectionTest extends org.specs2.mutable.Specification with Mockito {

  "A given list" >> {
    "should ignore specific elements when adding" >> {

      val DEFAULT_CATEGORY = 5

      val matchingCategoryElement = Element("a", DEFAULT_CATEGORY)
      val notMatchingCategoryElement = Element("b", DEFAULT_CATEGORY + 5)
      val elements = List(matchingCategoryElement, notMatchingCategoryElement).asJavaCollection

      val base = new ArrayList[Element]()
      val list = CollectionFactory(base).matchOnInsert(_.category == DEFAULT_CATEGORY)

      list.add(matchingCategoryElement)
      list.add(notMatchingCategoryElement)

      assert(list.contains(matchingCategoryElement) == true)
      assert(list.contains(notMatchingCategoryElement) == false)
      assert(list.containsAll(elements) == false)

      there was none
    }

    "should delegate calls to the given base list" >> {

      val someElement = Element("some", 0)
      val elementCollection = List(someElement).asJavaCollection
      val base = mock[ArrayList[Element]]

      base.clear()
      base.contains(someElement)
      base.containsAll(elementCollection)
      base.isEmpty()
      base.iterator()
      base.remove(someElement)
      base.removeAll(elementCollection)
      base.retainAll(elementCollection)
      base.size()
      base.toArray[Element](Array[Element]())
      base.toArray()

      there was one(base).clear()
      there was one(base).contains(someElement)
      there was one(base).containsAll(elementCollection)
      there was one(base).isEmpty()
      there was one(base).iterator()
      there was one(base).remove(someElement)
      there was one(base).removeAll(elementCollection)
      there was one(base).retainAll(elementCollection)
      there was one(base).size()
      there was one(base).toArray[Element](Array[Element]())
      there was one(base).toArray()
    }
  }

}