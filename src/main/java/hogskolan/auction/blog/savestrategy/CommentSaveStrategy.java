package hogskolan.auction.blog.savestrategy;

import hogskolan.auction.blog.entity.Comment;

public interface  CommentSaveStrategy {
    public void save(Comment comment);
}
