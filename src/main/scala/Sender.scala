import java.util.concurrent.atomic.AtomicReference
import akka.actor.Actor

class Sender extends Actor {
  @volatile var numEchoes = 0
  
  def receive = {
    case Echo => {
      numEchoes += 1
      if(numEchoes < Main.NUMBER_OF_ECHOES) {
        println("Heard an echo...")
      }
      else {
        // all echoes have been received.
        println("All echoes heard.")
        context.system.terminate
      }
    }
  }
}