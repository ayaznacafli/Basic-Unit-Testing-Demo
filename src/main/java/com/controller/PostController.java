package com.controller;

import com.domain.Comment;
import com.domain.Post;
import com.exception.ResourceNotFoundException;
import com.repository.CommentRepository;
import com.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("URL")
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }
    @GetMapping("URL")
    public List<Post> listPosts(){
        return postRepository.findAll();
    }
    @GetMapping(value = "URL/{id}")
    public Post getPost(@PathVariable("id") Integer id){
        return postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not post found with id="+id));
    }
    @PostMapping(value = "URL/{id}")
    public Post editPost(@PathVariable("id") Integer id, @RequestBody Post post){
        postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not post found with id="+id));
        return postRepository.save(post);
    }
    @DeleteMapping(value = "URL/{id}")
    public void deletePost(@PathVariable("id") Integer id){
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not post found with id="+id));
        postRepository.deleteById(post.getId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "URL/{id}/comments")
    public void createPostComment(@PathVariable("id") Integer id, @RequestBody Comment comment){
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not post found with id="+id));
        post.getComments().add(comment);
    }
    @DeleteMapping(value = "URL/{postId}/comments/{commentId}")
    public void deletePostComment(@PathVariable("postId") Integer postId,
                                  @PathVariable("commentId") Integer commentId){
        commentRepository.deleteById(commentId);
    }

    @PostMapping("URL/")
    public ResponseEntity<Post> createPost(@RequestBody @Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(post, HttpStatus.BAD_REQUEST);
        }
        Post savedPost = postRepository.save(post);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader1", "MyValue1");
        responseHeaders.set("MyResponseHeader2", "MyValue2");
        return new ResponseEntity<>(savedPost, responseHeaders, HttpStatus.CREATED);
    }


}
