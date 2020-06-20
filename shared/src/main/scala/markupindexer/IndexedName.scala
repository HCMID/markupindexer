package edu.holycross.shot.mid.markupindexer
import scala.scalajs.js.annotation._

import edu.holycross.shot.cite._

/** Mapping of a text or group of texts identified by CtsUrn to
* a list of [[MidMarkupReader]]s.
*
* @param passage Text or texts these markup readers can be applied to.
* @param person Unique URN for person.
* @param text Tagged text in source XML.
*/
@JSExportAll  case class IndexedName(passage: CtsUrn, person: Cite2Urn, text: String)
