import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._

object Main {
  def main(args: Array[String]) {
    implicit val system = ActorSystem("hot-dog")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route = path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`application/json`, "{\"name\": \"bob\"}"))
      }
    }

    val port = 9110

    Http().bindAndHandle(route, "localhost", port)

    println(s"Started server on port $port.\n\n")

  }
}