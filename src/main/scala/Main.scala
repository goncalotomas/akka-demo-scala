import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.actor.Actor
import akka.actor.Props


object Main {
  val NUMBER_OF_ECHOES = 10 

  def main(args: Array[String]): Unit = {
    // Create the actor system that will spawn our actors
    val actorSystem = ActorSystem("akka4scala");

    // Create the actor that will send out the messages
    val sender = actorSystem.actorOf(Props[Sender], "sender")

    var actorList = List[ActorRef]()

    for(i <- 1 to NUMBER_OF_ECHOES) {
      val receiver = actorSystem.actorOf(Props[Receiver], "receiver"+i)
      actorList ::= receiver
    }
  
    // Finally... Send each receiver a message using the tell method
    println("ECHO!");
    for(receiver <- actorList) {
      // the function should be called on the actor that will receive the message
      // the first argument is the message you want to send
      // the second argument is a reference to the sender
      // uncomment the following line if you wish
      // System.out.println("Sent an echo to "+receiver.path().name());
      receiver.tell(Echo,sender)
    }
  }
}