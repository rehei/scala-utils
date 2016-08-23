package com.github.rehei.scala.utils.collections

import java.util.ArrayList

import com.github.rehei.scala.utils.collections.model.Element
import org.specs2._
import org.specs2.mock.Mockito
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MatchOnInsertCollectionTest extends org.specs2.mutable.Specification with Mockito {

  val base = mock[ArrayList[Element]]

  val someElement = Element("abc", 5)

  "A wrapped list should" >> {
    "should delegate some calls" >> {

      val list = CollectionFactory(base).matchOnInsert(_.category == 1)

      list.contains(someElement)

      there was one(base).contains(someElement)
    }
  }

}