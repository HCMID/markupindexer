package edu.holycross.shot.mid.markupindexer
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import scala.xml._

class TeiPersNameIndexerSpec extends FlatSpec {

  "The TeiPersNameIndexer object" should "recursively collect find persName elements in XML containers"  in  {
    val xml = """<div n="1"><ab>Text including name of <persName n="urn:cite2:dummy:test.v1:1">Agamemnon</persName></ab></div>"""
    val root = XML.loadString(xml)
    val actual = TEIpersNameIndexer.indexedNames(CtsUrn("urn:cts:dummy:madeUp.v1:1"),root)
    println("ACTUAL:\n" + actual)

    //assert(actual.size == 1)
  }




}
