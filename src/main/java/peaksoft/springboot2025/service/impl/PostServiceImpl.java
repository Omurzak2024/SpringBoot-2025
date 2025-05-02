package peaksoft.springboot2025.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springboot2025.entity.Post;
import peaksoft.springboot2025.entity.User;
import peaksoft.springboot2025.repository.PostRepository;
import peaksoft.springboot2025.repository.UserRepository;
import peaksoft.springboot2025.service.PostService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void savePost(Long userId, Post post) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException(String.format("User with id %s not found", userId))
        );
        post.setUser(user);
        postRepository.save(post);

    }

    @Override
    public List<Post> getAllPostsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User not found with id: " + userId)
        );
        return user.getPosts();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException(String.format("Post with id %s not found", postId))
        );
    }

    @Override
    public void updatePost(Long postId, Post newPost) {
        Post oldPost = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException(String.format("Post with id %s not found", postId))
        );
        oldPost.setTitle(newPost.getTitle());
        oldPost.setDescription(newPost.getDescription());
        oldPost.setImage(newPost.getImage());
        oldPost.setDate(newPost.getDate());

        postRepository.save(oldPost);

    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException(String.format("Post with id %s not found", postId))
        );
        postRepository.delete(post);

    }

    @Override
    public List<Post> searchPosts(String word) {
        return postRepository.searchPosts(word);
    }
}
