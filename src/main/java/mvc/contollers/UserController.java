package mvc.contollers;

import mvc.models.User;
import mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserService service;

    @Autowired
    public void setService(@Qualifier("UserServiceImpl") UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String users(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getUser(@PathVariable("id") long id) {
        return "" + service.getUserById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public String addUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email
            ) {
        return "" + service.addUser(new User(username, password, email));
    }

    @GetMapping("/add_user")
    public String NewUser(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/add_user")
    public String AddNewUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUserID(
            @PathVariable("id") long id,
            Model model
    ) {
        User user = service.getUserById(id);
        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        return "update_user";

    }

    @PostMapping("/update/{id}")
    public String UpdateCurrentUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        user.setId(id);
        service.updateUser(user);
        return "redirect:/";
    }

    @PutMapping("/update")
    @ResponseBody
    public String updateUser(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "email", required = false) String email
    ) {
        return "" + service.updateUser(new User(id, username, password, email));
    }

    @PostMapping ("/remove/{id}")
    public String removeUser(
            @PathVariable("id") long id
    ) {
        User user = service.getUserById(id);
        if (user != null) {
            service.removeUser(user);
        }

        return "redirect:/";
    }

}
