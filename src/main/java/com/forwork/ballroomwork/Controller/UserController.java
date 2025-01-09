package com.forwork.ballroomwork.Controller;

import com.forwork.ballroomwork.Entity.User;
import com.forwork.ballroomwork.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // mainMenu
    @GetMapping("/")
    public String showMainMenu() {
        return "Start";
    }

    //show user /add page
    @GetMapping("/add")
    public String showAddUserPage() {
        return "Add";
    }

    // new users form
    @PostMapping("/save")
    public String saveUser(@RequestParam String name,
                           @RequestParam String contact,
                           @RequestParam String category,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                           @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time,
                           @RequestParam String gender) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setContact(contact);
        newUser.setCategory(category);
        newUser.setDate(date);
        newUser.setTime(java.sql.Time.valueOf(time));
        newUser.setGender(gender);

        newUser.AdjustTimeByGender();
        userService.save(newUser);
        return "redirect:/all";
    }
    @GetMapping("/all")
    public String showAllUsersPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "all"; // show all clients
    }
    @DeleteMapping("/all/delete/{id}")
    public String deleteEntity(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return "redirect:/all";
        } catch (IllegalArgumentException e) {
            return "redirect:/all?error=" + e.getMessage();
        }
    }
}