package com.github.rehei.scala.utils.collections

import java.util.ArrayList

import scala.collection.JavaConverters.asJavaCollectionConverter

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

import com.github.rehei.scala.utils.collections.model.Element

class ModifyOnInsertCollectionTest {

  val DEFAULT_CATEGORY = 5

  @Test
  def test() {
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
  }

}