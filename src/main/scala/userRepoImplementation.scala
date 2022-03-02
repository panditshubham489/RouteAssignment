import scala.collection.mutable.ListBuffer

class userRepoImplementation extends userRepo {

  val userList = ListBuffer.empty[String]
  override def insert(user: user): Unit=userList.append(user.name)
  override def getUser(name: String): String = userList.filter(_.contains(name)).mkString(",")

  override def getAll: String = userList.mkString(",")

  override def deleteUser(name: String): Unit = userList.remove(userList.indexOf(name))

}
