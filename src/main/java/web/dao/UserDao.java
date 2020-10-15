package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeById(Long id);
    User getUserById(Long id);
    List<User> listUsers();
    User findByUsername(String username);

}
