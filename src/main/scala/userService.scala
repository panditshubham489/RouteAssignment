class userService (userRepo: userRepo){
  def getUser(id:String): String = userRepo.getUser(id)
  def insert(str:user): Unit= userRepo.insert(str)
  def getAllUser: String= userRepo.getAll
  def deleteUser(name: String): Unit= userRepo.deleteUser(name)
}
