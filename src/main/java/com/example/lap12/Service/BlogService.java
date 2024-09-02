package com.example.lap12.Service;

import com.example.lap12.Model.Blog;
import com.example.lap12.Model.User;
import com.example.lap12.Repository.AuthRepository;
import com.example.lap12.Repository.BlogRepository;
import com.example.lap12.Api.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public void addBlog(Integer userId ,Blog blog){
        User user = authRepository.findUserById(userId);

        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId ,Blog blog, Integer blogId){
        User user = authRepository.findUserById(userId);
        Blog blog1 = blogRepository.findBlogById(blogId);
        if(blog1==null){
            throw new ApiException("blog not found");
        }
        if(blog1.getUser().getId() != user.getId()){
            throw new ApiException("User id mismatch");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
    }

    public void deleteBlog(Integer userId , Integer blogId){
        User user = authRepository.findUserById(userId);
        Blog blog1 = blogRepository.findBlogById(blogId);
        if(blog1==null){
            throw new ApiException("blog not found");
        }
        if(blog1.getUser().getId() != user.getId()){
            throw new ApiException("User id mismatch");
        }
        user.getBlogs().remove(blog1);
        blogRepository.delete(blog1);

    }

    public List<Blog> getUserBlogs(Integer userId){
        User user = authRepository.findUserById(userId);
        return blogRepository.findAllByUser(user);
    }

    public Blog getBlogById(Integer userId , Integer blogId){
        User user = authRepository.findUserById(userId);
        Blog blog1 = blogRepository.findBlogById(blogId);
        if(blog1==null){
            throw new ApiException("blog not found");
        }
        if(blog1.getUser().getId() != user.getId()){
            throw new ApiException("User id mismatch");
        }
        return blog1;
    }

    public Blog getBlogByTitle(Integer userId , String title){
        User user = authRepository.findUserById(userId);
        Blog blog1 = blogRepository.findBlogByTitle(title);
        if(blog1==null){
            throw new ApiException("blog not found");
        }
        if(blog1.getUser().getId() != user.getId()){
            throw new ApiException("User id mismatch");
        }
        return blog1;
    }


}
