package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
// Tells the compiler that this class will extend HttpsServlet and set up our class to control different URI Patterns
@Controller
public class TestController {
// Get Request Mapping for /test
    @GetMapping("/test")
//    Set repsone content type to text/html
    @ResponseBody
//    define method that will return text/html
    public String test(){
        return "Hello World";
    }

//    parks
    @GetMapping("/parks/{park}/{message}")
    @ResponseBody
    public String parks(@PathVariable String park, @PathVariable String message){
        return "<h1>Welcome to " + park + "</h1><p>" + message + "</p>";
    }
}
