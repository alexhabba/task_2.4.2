package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;
import web.service.UserDetailsServiceImpl;

import java.util.Collections;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;
    private RoleDao roleDao;

    @Autowired
    public RegisterController(UserDetailsServiceImpl userDetailsService,
                              PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    @GetMapping
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerNewUser(@ModelAttribute User user) {
        if (userDetailsService.findByUsername(user.getUsername()) == null) {
            if (user.getPassword().equals(user.getPasswordRepeat())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Collections.singleton(roleDao.findById(1L)));
                userDetailsService.addUser(user);
            }
        }
        return "redirect:/login";
    }
}
