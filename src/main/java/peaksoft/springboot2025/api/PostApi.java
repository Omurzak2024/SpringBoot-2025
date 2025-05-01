package peaksoft.springboot2025.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springboot2025.entity.Post;
import peaksoft.springboot2025.service.PostService;
import peaksoft.springboot2025.service.UserService;

@Controller
@RequestMapping("/api/posts/{userId}")
@RequiredArgsConstructor
public class PostApi {

    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public String getAll(@PathVariable Long userId, Model model) {
        model.addAttribute("posts", postService.getAllPostsByUserId(userId));
        model.addAttribute("userId",userId);
        return "post/allPosts";
    }

    @GetMapping("/new")
    public String createPost(@PathVariable Long userId, Model model) {
        model.addAttribute("newPost", new Post());
        model.addAttribute("userId", userId);
        return "post/newPost";
    }

    @PostMapping("/save")
    public String save(@PathVariable Long userId, @ModelAttribute("newPost") Post post) {
        postService.savePost(userId, post);
        return "redirect:/api/posts/" + userId; // 👈 туура багыттоо
    }
}
