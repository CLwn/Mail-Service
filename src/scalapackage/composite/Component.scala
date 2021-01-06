package scalapackage.composite

trait Component {
  val name:String
  val nameVal:String
  var tabs:Int
  def printTree(): Unit
  def getMail(): Unit

}
