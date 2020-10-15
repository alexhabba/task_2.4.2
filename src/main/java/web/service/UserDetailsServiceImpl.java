package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        User user = findByUsername(s);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not find");
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void removeById(Long id) {
        userDao.removeById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
