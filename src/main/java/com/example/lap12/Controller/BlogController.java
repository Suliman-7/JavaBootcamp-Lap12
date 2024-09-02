package com.example.lap12.Controller;


import com.example.lap12.Model.Blog;
import com.example.lap12.Model.User;
import com.example.lap12.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor


public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all-blogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping("/add-blog")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user , @RequestBody Blog blog) {
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body("blog added successfully");
    }

    @PutMapping("/update-blog/{bid}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user , @RequestBody Blog blog , @PathVariable int bid) {
        blogService.updateBlog(user.getId(),blog,bid);
        return ResponseEntity.status(200).body("blog updated successfully");
    }

    @DeleteMapping("/delete-blog/{bid}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user , @PathVariable int bid) {
        blogService.deleteBlog(user.getId(),bid);
        return ResponseEntity.status(200).body("blog deleted successfully");
    }

    @GetMapping("/get-user-blogs")
    public ResponseEntity getUserBlog(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogService.getUserBlogs(user.getId()));
    }

    @GetMapping("/get-by-id/{bid}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user , @PathVariable int bid) {
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),bid));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user , @PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(),title));
    }
}
