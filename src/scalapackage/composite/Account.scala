package scalapackage.composite

import structure.{Mailbox}

class Account(val name:String, var mailbox: Mailbox) extends Component {
  val nameVal = "Account"
  var tabs = 0;
  override def printTree(): Unit = print("@"+name+"\t")

  override def getMail(): Unit = {
    mailbox.updateMail(name)
    val list = mailbox.listMail()
    list.forEach(println(_))

  }
}
