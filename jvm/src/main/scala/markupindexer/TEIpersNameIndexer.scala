package edu.holycross.shot.mid.markupindexer

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._
import edu.holycross.shot.xmlutils._
import scala.xml._


object TEIpersNameIndexer extends MidMarkupIndexer {
  
  def verb = Cite2Urn("urn:cite2:cite:verbs.v1:appearsIn")
  def indexedNode(cn: CitableNode): Set[CiteTriple] = Set.empty[CiteTriple]
}
