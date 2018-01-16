package com.monkeys

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object Main {
  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem("hot-dog")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val port = 9110

    Http().bindAndHandle(Routing.routes, "localhost", port)

    println(s"Started server on port $port.\n\nHit ctrl-C to stop.")
  }
}