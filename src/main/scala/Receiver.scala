import akka.actor.Actor

class Receiver extends Actor {
  def receive = {
    case Echo => sender ! Echo
  }
}