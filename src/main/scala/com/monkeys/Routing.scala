package com.monkeys

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object Routing {
  val routes: Route = {
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`application/json`, "{\"name\": \"bob\"}"))
      }
    } ~
    pathPrefix("compute") {
      pathPrefix("fft") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, "{}"))
        }
      }
    }
  }
}
