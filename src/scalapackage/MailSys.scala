package scalapackage

import factorypattern.MailStoreMemoryFactory
import scalapackage.composite.{Account, Domain}
import structure.{MailSystem, Message}
import java.sql.Timestamp


//obect es el main de java i think..
object MailSys extends scala.App {

  val mailSystem = new MailSystem()
  mailSystem.createMailStore(new MailStoreMemoryFactory)

  val root = new Domain("")
  val cat = new Domain("cat")
  val urv = new Domain("urv")
  val etse = new Domain("etse")
  val estudiants = new Domain("estudiants")

  var mailbox1 = mailSystem.createNewUser("user1", "user1", 1998)
  var mailbox2 = mailSystem.createNewUser("user2", "user2", 1997)
  var mailbox3 = mailSystem.createNewUser("user3", "user3", 1996)
  var mailbox4 = mailSystem.createNewUser("user4", "user4", 1995)

  val user1 = new Account("user1", mailbox1)
  val user2 = new Account("user2", mailbox2)
  val user3 = new Account("user3", mailbox3)
  val user4 = new Account("user4", mailbox4)


  mailbox1.sendMail(new Message(user1.name, user2.name, "Hello", "How are u?", new Timestamp(System.currentTimeMillis())))
  mailbox2.sendMail(new Message(user2.name, user4.name, "Teammates", "How are u?", new Timestamp(System.currentTimeMillis())))
  mailbox3.sendMail(new Message(user3.name, user2.name, "Bye", "See u soon", new Timestamp(System.currentTimeMillis())))
  mailbox1.sendMail(new Message(user1.name, user3.name, "Happy new year", "happy new year", new Timestamp(System.currentTimeMillis())))
  mailbox3.sendMail(new Message(user3.name, user1.name, "Happy new year", "happy new year", new Timestamp(System.currentTimeMillis())))

  root.addChild(cat)
  cat.addChild(urv)
  urv.addChild(etse)
  urv.addChild(estudiants)
  cat.addChild(user1)
  urv.addChild(user2)
  etse.addChild(user3)
  estudiants.addChild(user4)

  //urv.printTree()
  urv.printTree()
  println()
  println("-------GET MAILS---------")
  urv.getMail()
 /* root.addChild(user1)
  root.addChild(cat)
  cat.addChild(user2)
  root.printTree()
*/
}
