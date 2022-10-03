package mvc.services;

import mvc.dao.UserDAO;
import mvc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "UserServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO dao;

    @Autowired
    public void setDao(@Qualifier("userdao") UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    @Override
    public User addUser(User user) {
        dao.addUser(user);
        return user;
    }

    @Override
    public void removeUser(User user) {
        dao.removeUser(user.getId());
    }

    @Override
    public User updateUser(User user) {
        dao.updateUser(user);
        return user;
    }
}
