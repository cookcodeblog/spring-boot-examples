package cn.xdevops.springboot.examples.springclouddemo.feign;

import cn.xdevops.springboot.examples.springclouddemo.feign.client.PostClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostClient postClient;

    public PostController(PostClient postClient) {
        this.postClient = postClient;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postClient.getPosts();
    }

    @GetMapping("/posts/{postId}")
    Post getPostById(@PathVariable("postId") Long postId) {
        return postClient.getPostById(postId);
    }
}
