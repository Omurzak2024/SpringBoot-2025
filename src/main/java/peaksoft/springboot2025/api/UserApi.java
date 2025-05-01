package peaksoft.springboot2025.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springboot2025.entity.User;
import peaksoft.springboot2025.service.UserService;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "user/getAllUsers";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("newUser", new User());
        return "user/createUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute ("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/api/users";
    }

    @GetMapping("/{id}/get")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("updateUser", userService.getUserById(id));
        return "user/updateUser";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, @ModelAttribute ("updateUser") User user) {
        userService.updateUserById(id, user);
        return "redirect:/api/users";
    }

    @GetMapping("{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/api/users";
    }

    @GetMapping("/{email}/getUser")
    public String getUserByEmail(@PathVariable String email, Model model) {
        model.addAttribute("updateUser", userService.getUserByEmail(email));
        return "user/updateUser";
    }
}
