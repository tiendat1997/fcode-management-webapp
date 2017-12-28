package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Post;
import com.tiendat.spring_webmvc.BootDemo.respository.PostActionRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.PostRepository;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostActionRepository postActionRepository;

    @Override
    public List<Post> findAllPost() {
        return this.postRepository.findAll();
    }

    @Override
    public List<Post> findPostByName(String name) {
        return this.postRepository.findByNameContaining(name);
    }

    @Override
    public List<Post> findPostByEventId(int eventId) {
        return this.postRepository.findByEventId(eventId);
    }

    @Override
    public Post findPostByPostId(int postId) {
        return this.postRepository.findByPostId(postId);
    }

    @Override
    public Post insertPost(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public List<Post> findPublicPost() {
        return this.postRepository.findByNotPublicIsFalse();
    }

}
