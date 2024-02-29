List<User> userList = [];
User loggedInUser = User(username: "None", password: "None");

class User {
  String username;
  String password;

  User({required this.username, required this.password});
}
