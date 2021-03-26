package hogskolan.auction.blog.controllers;

import hogskolan.auction.blog.entity.User;
import hogskolan.auction.blog.repository.UserRepository;
import hogskolan.auction.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/")
    public String showIndex(Model model) {
        return "indexview";
    }

    @GetMapping("/initdb")
    public String init() {
        User user1 = new User();
        user1.setName("Adrian");
        user1.setPassword(encoder.encode("a"));
        user1.setEmail("a@a.a");
        user1.setRole("ROLE_BLOGGER");
        user1.setStatus(1);

        User user2 = new User();
        user2.setName("Jens");
        user2.setPassword(encoder.encode("b"));
        user2.setEmail("b@b.b");
        user2.setRole("ROLE_FOLLOWER");
        user2.setStatus(1);

        userRepository.save(user1);
        userRepository.save(user2);
        return "redirect:/";
    }
}
