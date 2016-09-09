package com.github.rehei.scala.utils.collections

import java.util.ArrayList

import com.github.rehei.scala.utils.collections.model.Element
import org.specs2._
import org.specs2.mock.Mockito
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import org.junit.Assert._

@RunWith(classOf[JUnitRunner])
class ModifyOnInsertCollectionTest extends org.specs2.mutable.Specification with Mockito {

  val DEFAULT_CATEGORY = 5

  "A ModifyOnInsertList list" >> {
    "should update elements on insert" >> {

      val elementA = Element("a", DEFAULT_CATEGORY + 5)
      val elementB = Element("b", DEFAULT_CATEGORY + 6)
      val elementC = Element("c", DEFAULT_CATEGORY + 7)
      val elementD = Element("d", DEFAULT_CATEGORY + 8)

      val elementsCandD = List(elementC, elementD).asJavaCollection

      val base = new ArrayList[Element]()
      val result = CollectionFactory(base).modifyOnInsert(_.category = DEFAULT_CATEGORY)
      result.add(elementA)
      result.add(elementB)
      result.addAll(elementsCandD)

      assertTrue(elementA.category == DEFAULT_CATEGORY)
      assertTrue(elementB.category == DEFAULT_CATEGORY)
      assertTrue(elementC.category == DEFAULT_CATEGORY)
      assertTrue(elementD.category == DEFAULT_CATEGORY)
      assertTrue(result.contains(elementA))
      assertTrue(result.contains(elementB))
      assertTrue(result.containsAll(elementsCandD))
      assertTrue(result.size() == 4)
      assertFalse(result.isEmpty())

      there was none
    }
  }

}