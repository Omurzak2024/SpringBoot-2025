package peaksoft.springboot2025.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import peaksoft.springboot2025.entity.Post;
import peaksoft.springboot2025.service.PostService;
import peaksoft.springboot2025.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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
    public String savePost(@PathVariable Long userId,
                           @ModelAttribute Post post,
                           @RequestParam("imageFile") MultipartFile imageFile) throws IOException, IOException {
        if (!imageFile.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path uploadDir = Paths.get("src/main/resources/static/images");
            Files.createDirectories(uploadDir);
            Path filePath = uploadDir.resolve(fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            post.setImage("/images/" + fileName);
        }

        postService.savePost(userId, post);
        return "redirect:/api/posts/" + userId;
    }


    @GetMapping("/search")
    public String search(@PathVariable Long userId, @RequestParam String word, Model model) {
        model.addAttribute("posts", postService.searchPosts(word));
        model.addAttribute("word", word);
        model.addAttribute("userId", userId);
        return "post/searchResult";
    }


    @GetMapping("/view/{postId}")
    public String viewPost(@PathVariable Long userId,
                           @PathVariable Long postId,
                           Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        model.addAttribute("userId",userId);
        return "post/viewPost"; // view-post.html файлы
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
