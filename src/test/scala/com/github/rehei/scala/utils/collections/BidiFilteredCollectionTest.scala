package com.github.rehei.scala.utils.collections

import org.junit.Test
import java.util.ArrayList

class BidiFilteredCollectionTest {

      class Element {
      var name: String = ""
      var category: Int = 0
    }
    
  
  @Test
  def testBidiFilteredCollection() = {

    var element1 = new Element()
    element1.name = "1"
    element1.category = 2

    var element2 = new Element()
    element2.name = "1"
    element2.category = 2
    
    val list = new ArrayList[Element]()

    list.add(element1)
    list.add(element2)

    Collectionx(list).bidiFilter()
      .addingObjectMustSatisfy(_ => element2.category == 2)

  }

}