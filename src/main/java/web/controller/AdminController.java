package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;
import web.service.UserDetailsServiceImpl;
import web.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String adminPage(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.listUsers());
//        userService.removeById(3l);
        return "admin";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String registerNewUser(@ModelAttribute User user) {
        if (userService.findByUsername(user.getUsername()) == null) {
            if (user.getPassword().equals(user.getPasswordRepeat())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Collections.singleton(roleDao.findById(1L)));
                userService.addUser(user);
            }
        }
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.removeById(id);
        return "redirect:/admin";
    }
}
