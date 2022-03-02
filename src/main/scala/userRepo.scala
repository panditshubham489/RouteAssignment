trait userRepo {
  def insert(name:user): Unit
  def getUser(name: String): String
  def getAll: String
  def deleteUser(name: String): Unit

}
