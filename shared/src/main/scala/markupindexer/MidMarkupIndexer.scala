package edu.holycross.shot.mid.markupindexer
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._

import scala.scalajs.js.annotation._

/** A class capable of reading marked up archival
* editions, and creating CITE relations.
*/
trait MidMarkupIndexer {


  def verb: Cite2Urn



  /**  Given a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cn A single node in archival format.
  */
  def indexedNode(cn: CitableNode): Set[CiteTriple]

  def indexedCorpus(c: Corpus): Set[CiteTriple] = Set.empty[CiteTriple]

  def indexedLibrary(lib: CiteLibrary): CiteRelationSet = CiteRelationSet(Set.empty[CiteTriple])



}
