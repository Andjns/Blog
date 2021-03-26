package hogskolan.auction.blog.savestrategy;
import hogskolan.auction.blog.entity.Comment;


public class SaveContext {
    private CommentSaveStrategy commentSaveStrategy;
    public SaveContext(CommentSaveStrategy commentSaveStrategy){
        this.commentSaveStrategy = commentSaveStrategy;
    }

    public void save (Comment comment){
        commentSaveStrategy.save(comment);

    }
}//end class
