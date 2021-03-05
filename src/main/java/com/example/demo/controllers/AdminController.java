package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/adminFunctions/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //Admin User Registration
    @GetMapping(value="adminRegistration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/registration");
        return modelAndView;
    }

    @PostMapping(value = "addAdmin")
    public ModelAndView createNewAdminUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/registration");
        } else {
            userService.saveAdminUser(user);
            modelAndView.addObject("successMessage", "Admin user has been registered successfully!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin/list-of-users");

        }
        return modelAndView;
    }

    @GetMapping(value="userRegistration")
    public ModelAndView userRegistration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/userRegistration");
        return modelAndView;
    }

    @PostMapping(value = "addUser")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/userRegistration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin/list-of-users");

        }
        return modelAndView;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/admin/list-of-users";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        try {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            return "admin/update-user";
        } catch (Exception ex) {
            return "admin/list-of-users";
        }
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            user.setId(id);
            return "admin/update-user";
        }
        if (request.isUserInRole("ADMIN")) {
            userService.saveAdminUser(user);
        }
        else {
            userService.saveUser(user);
        }
        model.addAttribute("successMessage", "User has been updated successfully!");
        model.addAttribute("users", userService.getAll());
        return "admin/list-of-users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        try {

            userService.deleteUser(id);
            model.addAttribute("users", userService.getAll());
            return "admin/list-of-users";
        } catch (Exception ex) {
            return "/admin/home";
        }
    }

    @GetMapping("setActive/{id}")
    public String setActive(@PathVariable("id") long id, Model model) {
        try {
            userService.setUserActive(id);
            model.addAttribute("users", userService.getAll());
            return "admin/list-of-users";
        } catch (Exception ex) {
            return "/admin/home";
        }
    }

    @GetMapping("setInactive/{id}")
    public String setInactive(@PathVariable("id") long id, Model model) {
        try {
            userService.setUserInactive(id);
            model.addAttribute("users", userService.getAll());
            return "admin/list-of-users";
        } catch (Exception ex) {
            return "/admin/home";
        }
    }

}
