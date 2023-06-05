package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping(path = "/posts")
    @ResponseBody
    public String postIndex(){
        return "This is the Index";
    }
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String postId(@PathVariable String id){
        return "display a post with a id: " + (id);
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
