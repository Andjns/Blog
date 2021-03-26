package hogskolan.auction.blog.repository;

import hogskolan.auction.blog.entity.Post;
import hogskolan.auction.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllByUser(User user, Pageable pageable);
}
