package edu.holycross.shot.mid.markupindexer

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._
import edu.holycross.shot.xmlutils._
import scala.xml._

import wvlet.log._

import scala.annotation.tailrec

object TEIpersNameIndexer extends MidMarkupIndexer  with LogSupport {

  def verb = Cite2Urn("urn:cite2:cite:verbs.v1:appearsIn")

  def indexedNames(context: CtsUrn, n: xml.Node, names: Vector[IndexedName] = Vector.empty[IndexedName]) : Vector[IndexedName]= {
    val newNameList : Vector[IndexedName] = n match {
      case t: xml.Text =>  {
        names
      }

      case e: xml.Elem =>  {
        e.label match {
          case "persName" => {
            try {
              val urn = e.attribute("n").head.text
              val persName = TextReader.collectText(e)
              debug("found " + urn + " " + persName)
              val indexedName = IndexedName(context, Cite2Urn(urn), persName)
              val augmented = names :+ indexedName
              debug("augmented list " + augmented)
              augmented

            } catch {
              case t: Throwable => {
                debug("FAILED on " + e)
                names
              }
            }
            // get urn, get text, create IndexedName

          }
          case _ => {
            val collected = for (ch <- e.child) yield {
              indexedNames(context, ch, names)
            }
            debug("COLLECTED: " + collected.flatten)
            collected.toVector.flatten
          }
        }
      }
     }
     debug("newNameList now " + newNameList)
     newNameList
  }

  // Triple is person, verb, text
  def nameToTriple(indexedName: IndexedName): CiteTriple = {
    CiteTriple(indexedName.person, verb, indexedName.passage)
  }

  def indexedNode(cn: CitableNode): Set[CiteTriple] = {
    val root = XML.loadString(cn.text)
    val nameSet = indexedNames(cn.urn, root)
    nameSet.toVector.map(n => nameToTriple(n)).toSet
  }
}
