package scalapackage.composite

import scala.collection.mutable.ListBuffer

class Domain(val name:String) extends Component {
  private var children = new ListBuffer[Component]
  val nameVal = "Domain"
  var tabs = 0

  def addChild(child:Component): Unit ={
    if (child.nameVal=="Domain") child.tabs+=1
    children+=child
  }


  override def printTree(): Unit = {

    println("/"+name)
    print("|-->")
    children.sortBy(_.name).reverse.foreach(_.printTree())
    //children.foreach(_.printTree())


  }

  override def getMail(): Unit = {
    children.map(_.getMail())
  }
}
