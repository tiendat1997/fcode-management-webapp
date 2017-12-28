package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Post;
import com.tiendat.spring_webmvc.BootDemo.model.PostAction;
import com.tiendat.spring_webmvc.BootDemo.service.PostActionService;
import com.tiendat.spring_webmvc.BootDemo.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostActionService postActionService;

    @GetMapping(value = "/all")
    public List<Post> getAll() {
        return this.postService.findAllPost();
    }

    @GetMapping(value = "/get", params = {"eventId"})
    public List<Post> getByEventId(@RequestParam("eventId") int eventId) {
        return this.postService.findPostByEventId(eventId);
    }

    @GetMapping(value = "/get/{postId}")
    public Post getPost(@PathVariable("postId") int postId) {
        return this.postService.findPostByPostId(postId);
    }

    @GetMapping(value = "/public")
    public List<Post> getPublicPost() {
        return this.postService.findPublicPost();
    }

    @GetMapping(value = "/new", params = {"name", "text", "eventId", "username"})
    public PostAction addPost(@ModelAttribute Post post, @RequestParam("username") String username) {

        this.postService.insertPost(post);
        PostAction postAction = new PostAction(username, post.getPostId(), 1, new Date());
        return this.postActionService.addAction(postAction);

    }

    @GetMapping(value = "/update", params = {"postId", "name", "text", "eventId", "username"})
    public PostAction updatePost(@ModelAttribute Post post, @RequestParam("username") String username) {
        this.postService.updatePost(post);
        return this.postActionService.addAction(new PostAction(username, post.getPostId(), 3, new Date()));
    }

}
