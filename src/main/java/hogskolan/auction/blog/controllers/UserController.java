package hogskolan.auction.blog.controllers;


import hogskolan.auction.blog.entity.Comment;
import hogskolan.auction.blog.repository.CommentRepository;
import hogskolan.auction.blog.repository.PostRepository;
import hogskolan.auction.blog.repository.UserRepository;
import hogskolan.auction.blog.savestrategy.CommentSaveStrategy;
import hogskolan.auction.blog.savestrategy.SaveContext;
import hogskolan.auction.blog.savestrategy.TextSaveStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;


    //Visas i tabellen i adminview
    @RequestMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("comments", commentRepository.findAll());
        return "bloggerview";
    }

    @Autowired
    PasswordEncoder encoder;

    //delete comment
    @GetMapping("/admin/comment/delete/{b_id}")
    public String deleteCommentById(@PathVariable Integer b_id) {
        Comment comment = commentRepository.findById(b_id).get();
        CommentSaveStrategy commentSaveStrategy = new TextSaveStrategy();
        SaveContext context = new SaveContext(commentSaveStrategy);
        context.save(comment);
        commentRepository.deleteById(b_id);
        return "redirect:/admin";
    }


}