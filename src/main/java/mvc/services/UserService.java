package mvc.services;

import mvc.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers ();
    User getUserById(long id);
    User addUser(User user);
    void removeUser(User user);
    User updateUser(User user);
}
