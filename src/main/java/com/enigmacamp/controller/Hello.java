package com.enigmacamp.controller;


import com.enigmacamp.model.Course;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping("/{name}")
    public String sayHello(@PathVariable("name") String name){
        return "Hello " + name;
    }

    //root
    @GetMapping()   
    public String sayHello2(){
        return "entah sampai kapan ya gais ya";
    }

}
