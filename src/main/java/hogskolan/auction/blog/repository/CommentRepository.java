package hogskolan.auction.blog.repository;

import hogskolan.auction.blog.entity.Comment;
import hogskolan.auction.blog.entity.Post;
import hogskolan.auction.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
   List<Comment> findByPost(Post post);
   List<Comment> findAllByUser(User user);
   List<Comment> findAllByPost(Post post);
}
