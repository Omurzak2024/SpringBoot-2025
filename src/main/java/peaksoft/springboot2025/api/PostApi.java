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

    @GetMapping("/search")
    public String search(@PathVariable Long userId, @RequestParam String word, Model model) {
        model.addAttribute("posts", postService.searchPosts(word));
        model.addAttribute("word", word);
        model.addAttribute("userId", userId);
        return "post/searchResult";
    }


    @GetMapping("/view/{postId}")
    public String viewPost(@PathVariable Long userId, @PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            // лог же башкача баракка жөнөтүү
            return "redirect:/api/posts/" + userId + "?error=post-not-found";
        }
        model.addAttribute("post", post);
        model.addAttribute("userId", userId);
        return "post/viewPost";
    }


    @GetMapping("/edit/{postId}")
    public String editPost(@PathVariable Long userId, @PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.getPostById(postId));
        model.addAttribute("userId", userId);
        return "post/editPost";
    }

    @PostMapping("/edit/{postId}")
    public String updatePost(@PathVariable Long userId,
                             @PathVariable Long postId,
                             @ModelAttribute("post") Post post) {
        postService.updatePost(postId, post); // 👈 Туура метод чакырыгы

        return "redirect:/api/posts/" + userId;
    }


    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/api/posts/" + userId;
    }



}
