package hogskolan.auction.blog.savestrategy;

import hogskolan.auction.blog.entity.Comment;
import hogskolan.auction.blog.entity.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextSaveStrategy implements CommentSaveStrategy {

    @Override
    public void save (Comment comment) {

        BufferedWriter writer;
        try {
            writer = Files.newBufferedWriter(Paths.get("comment.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            writer.write(comment.getCommenttext());
            writer.newLine();
            writer.close();
            System.out.println(comment.getCommenttext());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
