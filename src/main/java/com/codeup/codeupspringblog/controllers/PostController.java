package com.codeup.codeupspringblog.controllers;

import ch.qos.logback.core.model.Model;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping(path = "/posts")
//    @ResponseBody
//    public String postIndex(){
//        return "This is the Index";
//    }
    public String postIndex(Model model) {
        List<Post> posts = new ArrayList<>();
        Post p1 = new Post();
        p1.setTitle("post 1");
        p1.setBody("this desc1");
        Post p2 = new Post();
        p2.setTitle("post 2");
        p2.setBody("this desc2");
        posts.add(p1);
        posts.add(p2);
        model.addAttribute("posts", posts);
        return "posts/index";
    }
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public String postId(@PathVariable String id){
//        return "display a post with a id: " + (id);
    public String postId(@PathVariable int id, Model model) {
        Post post = new Post();
        post.setTitle("a shrimp");
        post.setBody("this desc");
        model.addAttribute("id",id);
        model.addAttribute("post",post);
        return "posts/show";
 }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String viewPostCreate() {
        return "View post creation form";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String postCreatedPost() {
        return "You created a post";
    }
}
