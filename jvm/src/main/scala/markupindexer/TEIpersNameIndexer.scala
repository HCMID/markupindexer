package edu.holycross.shot.mid.markupindexer

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.scm._
import edu.holycross.shot.xmlutils._
import scala.xml._

import scala.annotation.tailrec

object TEIpersNameIndexer extends MidMarkupIndexer {

  def verb = Cite2Urn("urn:cite2:cite:verbs.v1:appearsIn")

  def indexedNames(context: CtsUrn, n: xml.Node, names: Vector[IndexedName] = Vector.empty[IndexedName]) : Vector[IndexedName]= {
    n match {
      case t: xml.Text =>  {
        names
      }

      case e: xml.Elem =>  {
        e.label match {
          case "persName" => {
            println("FOUND ONE! ")
            try {
              val urn = e.attribute("n").head.text
              // CHANGE TO USE COLLECT TEXT FORM XML UTILS
              val persName = TextReader.collectText(e)
              println("it's " + urn + " " + persName)
              val indexedName = IndexedName(context, Cite2Urn(urn), persName)
              val augmented = names :+ indexedName
              println("RETURN AUG " + augmented)
              augmented

            } catch {
              case t: Throwable => {
                println("FAILED on " + e)
                names
              }
            }
            // get urn, get text, create IndexedName
            names
          }
          case _ => {
            for (ch <- e.child) {
              indexedNames(context, ch, names)
            }
          }
        }
      }
     }
     names
  }

  def indexedNode(cn: CitableNode): Set[CiteTriple] = {
    val root = XML.loadString(cn.text)
    Set.empty[CiteTriple]
  }
}
