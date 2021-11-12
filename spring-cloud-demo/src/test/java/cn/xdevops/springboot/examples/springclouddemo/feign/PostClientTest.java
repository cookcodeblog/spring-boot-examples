package cn.xdevops.springboot.examples.springclouddemo.feign;

import cn.xdevops.springboot.examples.springclouddemo.feign.client.PostClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
class PostClientTest {

    @Autowired
    private PostClient postClient;

    @Test
    @DisplayName("get posts")
    void getPosts() throws IOException {

        stubFor(get(urlEqualTo("/posts"))
                .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBodyFile("posts.json")));

        List<Post> posts = postClient.getPosts();
        assertThat(posts).hasSizeGreaterThan(0);
        assertThat(posts.get(0))
                .extracting(Post::getId, Post::getUserId, Post::getTitle, Post::getBody)
                .containsExactly(1L, 1L, "title", "body");
    }

    @Test
    @DisplayName("get post by id")
    void getPostById() {
        Post post = postClient.getPostById(1L);
        assertThat(post).extracting(Post::getId).isEqualTo(1L);
        System.out.println(post);

        Post post2 = postClient.getPostById(2L);
        assertThat(post2).extracting(Post::getId).isEqualTo(2L);
        System.out.println(post2);
    }

}