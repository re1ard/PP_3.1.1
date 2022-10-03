package mvc.dao;

import mvc.models.User;

import java.util.List;

public interface UserDAO {
    User getUserById(long id);
    void addUser(User user);
    void removeUser(long id);
    void updateUser(User user);

    List<User> getAllUsers();
}
