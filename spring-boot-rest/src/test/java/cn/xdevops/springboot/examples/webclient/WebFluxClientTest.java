package cn.xdevops.springboot.examples.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebFluxClientTest {

//    private WebClient webClient = WebClient.create();

    private WebClient webClient = WebClient
            .builder()
            .clientConnector(
                    new ReactorClientHttpConnector(HttpClient
                            .create()
                            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                            .doOnConnected(connection -> connection
                                    .addHandler(new ReadTimeoutHandler(10))
                            ))
            )
            .build();

    @Test
    @DisplayName("get github user details")
    void getGithubUserDetails() {
        String url = "https://api.github.com/users/cookcodeblog";
        ResponseEntity<String> response = webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(String.class).block();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("get a resource")
    void getAResource() {
        String url = url("/posts/1");
        Post post = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
        assertThat(post).hasNoNullFieldsOrProperties();
    }

    private String url(String path) {
        // use local json-server
        // https://github.com/typicode/json-server
        String root =  "http://localhost:3001";
        return root + path;
    }

    @Test
    @DisplayName("get resources list")
    void getResourcesList() {
        String url = url("/posts");
        Post[] posts = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Post[].class)
                .block();
        List<Post> postList =Arrays.asList(posts);
        assertThat(postList).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("use post to create resource")
    void usePostToCreateResource() {
        String url = url("/posts");
        Post newPost = new Post("foo", "bar", 1L);
        ResponseEntity<Post> response = webClient.post()
                .uri(url)
                .body(Mono.just(newPost), Post.class)
                .retrieve()
                .toEntity(Post.class)
                .block();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("use put to update resource")
    void usePutToUpdateResource() {
        String url = url("/posts/1");
        Post newPost = new Post("foo", "bar", 1L);
        Post updatedPost = webClient.put()
                .uri(url)
                .body(Mono.just(newPost), Post.class)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
        assertThat(updatedPost).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("delete resource")
    void deleteResource() {
        String url = url("/posts/2");
        webClient.delete()
                .uri(url);

    }

}
