package edu.holycross.shot.mid.markupindexer
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._

import scala.xml._

class TeiPersNameIndexerSpec extends FlatSpec {


  val xml = """<div n="1"><ab>Text including name of <persName n="urn:cite2:dummy:test.v1:1">Agamemnon</persName></ab></div>"""
  val root = XML.loadString(xml)
  val expectedId = Cite2Urn("urn:cite2:dummy:test.v1:1")
  val textUrn = CtsUrn("urn:cts:dummy:madeUp.v1:1")
  val cn = CitableNode(textUrn, xml)

  "The TeiPersNameIndexer object" should "recursively find persName elements in XML containers"  in  {
    val expectedSize = 1
    val expectedText = "Agamemnon"

    val actual = TEIpersNameIndexer.indexedNames(textUrn,root)

    assert(actual.size == expectedSize)
    assert(actual.head.text == expectedText)
  }

  it should "recursively collect CiteTriples from a Citable Node" in {

    val actual = TEIpersNameIndexer.indexedNode(cn).toVector

    val expectedSize = 1


    assert(actual.size == expectedSize)
    assert(actual.head.urn1 == expectedId)
    println(actual.head.urn2 == textUrn)

  }

  it should "identify the verb of its triple statements" in {
    assert(TEIpersNameIndexer.verb == Cite2Urn("urn:cite2:cite:verbs.v1:appearsIn"))
  }

  it should "inherit the method to index a corpus" in {
    val corpus = Corpus(Vector(cn))
    val actual = TEIpersNameIndexer.indexedCorpus(corpus).toVector

    val expectedSize = 1


    assert(actual.size == expectedSize)
    assert(actual.head.urn1 == expectedId)
    println(actual.head.urn2 == textUrn)
  }

  it should "inherit the method to index a library" in pending /*{
    val lib = CiteLibrary("Test lib", Cite2Urn("urn:cite2:dummy:lib.v1:1"),
    "Public domain", namespaces = Vector.empty, textRepository = )
  }*/


}
