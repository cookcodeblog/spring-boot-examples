package cn.xdevops.springboot.examples.springclouddemo.feign.client;

import cn.xdevops.springboot.examples.springclouddemo.feign.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "postClient", url="${client.post.baseUrl}")
public interface PostClient {

    @GetMapping("/posts")
    List<Post> getPosts();

    @GetMapping("/posts/{postId}")
    Post getPostById(@PathVariable("postId") Long postId);
}
