package com.github.rehei.scala.utils.collections

import com.github.rehei.scala.utils.collections.model.Element
import org.specs2._
import org.specs2.mock.Mockito
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import java.util.ArrayList
import com.github.rehei.scala.utils.collections.model.MappedElement

@RunWith(classOf[JUnitRunner])
class MapThroughCollectionTest extends org.specs2.mutable.Specification with Mockito {

  val DEFAULT_CATEGORY = 2

  "A mapped through list" >> {
    "should just work fine" >> {

      val element1 = Element("1", DEFAULT_CATEGORY)
      val element2 = Element("2", DEFAULT_CATEGORY)
      val element3 = Element("3", DEFAULT_CATEGORY + 2)
      val element4 = Element("4", DEFAULT_CATEGORY + 3)
      val element5 = Element("5", DEFAULT_CATEGORY + 4)

      val base = new ArrayList[MappedElement]()
      base.add(MappedElement(element1))
      base.add(MappedElement(element2))
      base.add(MappedElement(element3))

      val list = CollectionFactory(base)
        .mapThrough(m => m.element)
        .mapReverse(m => new MappedElement(m))

      list.add(element4)
      list.add(element5)

      assert(list.size() == 5)
      assert(list.contains(element1) == true)
      assert(list.contains(element2) == true)
      assert(list.contains(element3) == true)
      assert(list.contains(element4) == true)
      assert(list.contains(element5) == true)

      there was none
    }
  }

}