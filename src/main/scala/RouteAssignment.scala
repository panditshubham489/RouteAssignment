import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object RouteAssignment extends App {

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext
    val userRepo = new userRepoImplementation
    val UserService = new userService(userRepo)

    val route = {
      post{
        path("addUser"/ Segment){ userSegment =>
          UserService.insert(user(userSegment))
          complete(
            "User is Added"
          )
        }
      }~ get{
        path("getUser"/ Segment){ userSegment =>
          complete(
            UserService.getUser(userSegment)
          )
        }
      }~ get{
        path("getAllUsers"){
          complete(
            UserService.getAllUser
          )
        }
      }~ delete{
        path("DeleteUser"/ Segment){ userSegment=>
          UserService.deleteUser(userSegment)
          complete(
            "User is deleted"
          )
        }
      }
    }

  val bindingFuture = Http().newServerAt("localhost", 9000).bind(route)

    println(s"Server now online.")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
}