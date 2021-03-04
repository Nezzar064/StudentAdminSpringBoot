package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    //Admin User Registration
    @RequestMapping(value="/admin/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
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
            modelAndView.setViewName("admin/registration");

        }
        return modelAndView;
    }
    //User Registration

    @RequestMapping(value="/admin/userRegistration", method = RequestMethod.GET)
    public ModelAndView userRegistration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/userRegistration");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/userRegistration", method = RequestMethod.POST)
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
            modelAndView.setViewName("admin/userRegistration");

        }
        return modelAndView;
    }
}
