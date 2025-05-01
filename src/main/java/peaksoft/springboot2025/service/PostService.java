package peaksoft.springboot2025.service;

import peaksoft.springboot2025.entity.Post;

import java.util.List;

public interface PostService {
    void savePost(Long userId, Post post);
    List<Post> getAllPostsByUserId(Long userId);
    Post getPostById(Long postId);
    void updatePost(Long postId, Post newPost);
    void deletePost(Post post);

    List<Post> searchPosts(String word);

}
