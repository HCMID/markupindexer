package edu.holycross.shot.mid.markupindexer
import scala.scalajs.js.annotation._

import edu.holycross.shot.cite._

/** Mapping of a text or group of texts identified by CtsUrn to
* a list of [[MidMarkupReader]]s.
*
* @param urn Text or texts these markup readers can be applied to.
* @param indexers Indexers applicable to texts identified by [[urn]].
*/
@JSExportAll  case class IndexPairing(urn: CtsUrn, indexers: Vector[MidMarkupIndexer])
