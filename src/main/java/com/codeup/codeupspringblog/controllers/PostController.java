package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository usersDao;

    public PostController(PostRepository postsDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
//        Post post1= new Post(1L,"title", "description");
//        model.addAttribute("post", post1);
        model.addAttribute("post", postsDao.findById(id).get());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm() {
        return "/posts/create";
    }

    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String submitNewPost(@RequestParam (name="title")String title, @RequestParam(name="body") String body, Model model) {
        Post post = new Post(title, body);
        User user = usersDao.findById(1L).get();
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }
}

