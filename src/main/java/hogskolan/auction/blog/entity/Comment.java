package hogskolan.auction.blog.entity;

import javax.persistence.*;
import java.time.LocalDate;



@Entity
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer b_id;
    private String commenttext;
    private LocalDate createdComment;


    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Comment() {

    }

    public Comment(String commenttext) {
        this.commenttext = commenttext;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getB_id() {
        return b_id;
    }

    public void setB_id(Integer b_id) {
        this.b_id = b_id;
    }


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public LocalDate getCreatedComment() {
        return createdComment;
    }

    public void setCreatedComment(LocalDate createdComment) {
        this.createdComment = createdComment;
    }
}
