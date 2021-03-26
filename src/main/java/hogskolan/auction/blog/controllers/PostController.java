package hogskolan.auction.blog.controllers;

import hogskolan.auction.blog.entity.Post;
import hogskolan.auction.blog.entity.User;
import hogskolan.auction.blog.repository.PostRepository;
import hogskolan.auction.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    //Show all posts med paging
   @GetMapping("/posts/page/{pageno}")
    public String showPage(@PathVariable() Integer pageno, Model model) {
       Boolean no = true;
        if (pageno < 0 || pageno == null) {
            pageno = 0;
        }
       User user = userRepository.findByEmail(new SecurityController().loggedInUser());
        final int PAGESIZE = 3; //number of product on each page
        //get the next page based on its pagenumber, zerobased
        //also set pagesize, the number of products on the page
        PageRequest paging = PageRequest.of(pageno, PAGESIZE);
        Page<Post> pagedResult = postRepository.findAll(paging);
        List<Post> listProducts;
        //returns the page content our 3 products as List
        listProducts = pagedResult.getContent();
        model.addAttribute("currentPageNumber", pagedResult.getNumber()); //zerobased
        model.addAttribute("displayableCurrentPageNumber", pagedResult.getNumber() + 1);
        model.addAttribute("nextPageNumber", pageno + 1); //going forward to next page
        model.addAttribute("previousPageNumber", pageno - 1); //going backwards to previous page
        model.addAttribute("totalPages", pagedResult.getTotalPages());
        model.addAttribute("totalItems", pagedResult.getTotalElements());
        model.addAttribute("hasNext", pagedResult.hasNext());
        model.addAttribute("hasPrevious", pagedResult.hasPrevious());
        model.addAttribute("posts", listProducts);//set the list with the 3 products
       if(user.getRole().equals("ROLE_BLOGGER")){
           no = false;
       }
       model.addAttribute("no", no);
        return "postallview";

    }


    //Show all products med paging
    @GetMapping("/posts/user/page/{pageno}")
    public String showPageForUser(@PathVariable() Integer pageno, Model model) {
        Boolean no = true;
        if (pageno < 0 || pageno == null) {
            pageno = 0;
        }
        final int PAGESIZE = 3; //number of product on each page
        //get the next page based on its pagenumber, zerobased
        //also set pagesize, the number of products on the page

        User user = userRepository.findByEmail(new SecurityController().loggedInUser());


        Pageable all = PageRequest.of(pageno, PAGESIZE);
        List<Post> allProductsByUser = user.getPosts();

        PageRequest paging = PageRequest.of(pageno, PAGESIZE);
        Page<Post> pagedResult = postRepository.findAllByUser(user, paging);
        List<Post> listPost;
        //returns the page content our 3 products as List
        listPost = pagedResult.getContent();
        model.addAttribute("currentPageNumber", pagedResult.getNumber()); //zerobased
        model.addAttribute("displayableCurrentPageNumber", pagedResult.getNumber() + 1);
        model.addAttribute("nextPageNumber", pageno + 1); //going forward to next page
        model.addAttribute("previousPageNumber", pageno - 1); //going backwards to previous page
        model.addAttribute("totalPages", pagedResult.getTotalPages());
        model.addAttribute("totalItems", pagedResult.getTotalElements());
        model.addAttribute("hasNext", pagedResult.hasNext());
        model.addAttribute("hasPrevious", pagedResult.hasPrevious());
        model.addAttribute("posts", listPost);//set the list with the 3 products
        if(user.getRole().equals("ROLE_BLOGGER")){
            no = false;
        }
        model.addAttribute("no", no);

        return "postallview";

    }

    @Autowired
    SecurityController sec = new SecurityController();

    @RequestMapping("/posts/add")
    public String addPost(Model model) {
        return "postaddview";
    }
    @PostMapping("/posts/add")
    public String addPostToDB(Model model, @RequestParam Map<String, String> allFormRequestParams) {
        Post post = new Post();
        post.setTitle(allFormRequestParams.get("title"));
        post.setDescription(allFormRequestParams.get("description"));
        User user = userRepository.findByEmail(sec.loggedInUser());
        user.addPost(post);
        userRepository.save(user);
        if (user.getRole().equals("ROLE_BLOGGER")) {
            return "redirect:/admin";
        } else {
            return "redirect:/posts/page/0";
        }
    }

    //delete post
    @GetMapping("/posts/delete/{p_id}")
    public String deleteProductById(@PathVariable Integer p_id) {
        postRepository.deleteById(p_id);
        return "redirect:/admin";
    }

    //update post
    @GetMapping("/posts/update/{p_id}")
    public String updateProductById(Model model, @PathVariable Integer p_id) {
        model.addAttribute("post",postRepository.findById(p_id).get());
        return "postupdateview";
    }

    @PostMapping("/posts/update/{p_id}")
    public String updateProduct(@RequestParam Map<String, String> allFormRequestParams, Integer p_id) {
        Post post = postRepository.findById(p_id).get();
        post.setTitle(allFormRequestParams.get("name"));
        post.setDescription(allFormRequestParams.get("description"));
        postRepository.save(post);
        return "redirect:/admin";
    }

}//end Controller class
