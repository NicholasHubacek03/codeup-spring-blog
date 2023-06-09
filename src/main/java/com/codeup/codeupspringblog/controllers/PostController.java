package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Service.EmailService;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PostController {
    private final PostRepository postsDao;

    private final EmailService emailService;
    private final PostCategoriesRepository catDao;
    private final UserRepository usersDao;

    public PostController(PostRepository postsDao, EmailService emailService, PostCategoriesRepository catDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.emailService = emailService;
        this.catDao = catDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findById(id).get());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("categories", catDao.findAll());
        // pass a new Post object to the form
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, @RequestParam String userId){
        User user = usersDao.findById(Long.valueOf(userId)).get();
        post.setUser(user);
        postsDao.save(post);
        emailService.prepareAndSend(post,"You created a new post!", "Title: " + post.getTitle() + "\nBody: " + post.getBody());
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        if(postsDao.findById(id).isPresent()) {
            Post postToEdit = postsDao.findById(id).get();
            model.addAttribute("post", postToEdit);
        }
        return "posts/create";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post newPost) {
        User user = usersDao.findById(1L).get();
        newPost.setUser(user);
        postsDao.save(newPost);
        return "redirect:/posts";
    }
}
