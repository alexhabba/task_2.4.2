package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {


//    @Autowired
    public DataInit(RoleDao roleRepo, UserDao userRepo, PasswordEncoder passwordEncoder) {
        User user1 = new User("admin", passwordEncoder.encode("123"));
        User user2 = new User("alex", passwordEncoder.encode("123"));
        User user3 = new User("user", passwordEncoder.encode("123"));
        Set<User> users = new HashSet<>(Arrays.asList(user1, user2, user3));

        Role role1 = new Role("USER");
        Role role2 = new Role("ADMIN");
        Set<Role> roles = new HashSet<>(Arrays.asList(role1, role2));

        roleRepo.addRole(role1);
        roleRepo.addRole(role2);

        user1.setRoles(roles);
        user2.setRoles(new HashSet<>(Collections.singletonList(role1)));
        user3.setRoles(new HashSet<>(Collections.singletonList(role1)));

        userRepo.addUser(user1);
        userRepo.addUser(user2);
        userRepo.addUser(user3);

        System.out.println(userRepo.findByUsername("user"));
    }
}
