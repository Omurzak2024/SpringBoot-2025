package peaksoft.springboot2025.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.springboot2025.entity.Post;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository <Post, Long> {

    @Query("select p from Post p join p.user pu where pu.id=:userId")
    List<Post> getAllPostsByUserId(Long userId);

    @Query("select p from Post p where p.title ilike :word")
    List<Post> searchPosts(String word);
}
