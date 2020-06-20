package edu.holycross.shot.mid.markupindexer
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._

import scala.annotation.tailrec

import scala.scalajs.js.annotation._

/** A class capable of reading marked up archival
* editions, and creating CITE relations.
*/
trait MidMarkupIndexer {

  // Verb of triples in this index
  def verb: Cite2Urn

  /**  Given a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cn A single node in archival format.
  */
  def indexedNode(cn: CitableNode): Set[CiteTriple]


  @tailrec private def sumTriples(tripleSets: Vector[Set[CiteTriple]], resultSet: Set[CiteTriple] = Set.empty[CiteTriple]): Set[CiteTriple] = {
    if (tripleSets.isEmpty) {
      resultSet
    } else {
      sumTriples(tripleSets.tail, resultSet ++ tripleSets.head)
    }
  }

  def indexedCorpus(c: Corpus): Set[CiteTriple] = {
    val indexedNodes = c.nodes.map(n => indexedNode(n))
    sumTriples(indexedNodes)

  }

  def indexedLibrary(lib: CiteLibrary): CiteRelationSet = {
    val tripleSet = indexedCorpus(lib.textRepository.get.corpus)
    CiteRelationSet(tripleSet)
  }



}
