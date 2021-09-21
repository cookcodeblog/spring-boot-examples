package cn.xdevops.springboot.examples.webclient;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestTemplateTest {

//    private RestTemplate restTemplate = new RestTemplate();

    private RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(10))
            .setReadTimeout(Duration.ofSeconds(10))
            .build();

    private String url(String path) {
        // use local json-server
        // https://github.com/typicode/json-server
        String root =  "http://localhost:3001";
        return root + path;
    }

    @Test
    @DisplayName("get github user details")
    void getGithubUserDetails() {
        String url = "https://api.github.com/users/cookcodeblog";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("get plain JSON")
    void getPlainJSON() {
        String url = url("/posts/1");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    @DisplayName("get POJO with response entity")
    void getPOJOWithEntity() {
        String url = url("/posts/1");
        ResponseEntity<Post> response = restTemplate.getForEntity(url, Post.class);
        Post post = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(post).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("get POJO with object")
    void getPOJOWithObject() {
        String url = url("/posts/1");
        Post post = restTemplate.getForObject(url, Post.class);
        assertThat(post).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("get resource for list")
    void getResourcesForList() {
        String url = url("/posts");
        ResponseEntity<Post[]> response = restTemplate.getForEntity(url, Post[].class);
        List<Post> posts = Arrays.asList(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(posts).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("use post object to create a resource")
    void usePostObjectToCreateAResource() {
        String url = url("/posts");
        Post newPost = new Post("foo", "bar", 1L);
        HttpEntity<Post> request = new HttpEntity<>(newPost);
        Post createdPost = restTemplate.postForObject(url, request, Post.class);
        assertThat(createdPost).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("use post entity to create a resource")
    void usePostEntityToCreateAResource() {
        String url = url("/posts");
        Post newPost = new Post("foo", "bar", 1L);
        HttpEntity<Post> request = new HttpEntity<>(newPost);
        ResponseEntity<Post> response = restTemplate.postForEntity(url, request, Post.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("use exchange to create a resource")
    void useExchangeToCreateAResource() {
        String url = url("/posts");
        Post newPost = new Post("foo", "bar", 1L);
        HttpEntity<Post> request = new HttpEntity<>(newPost);
        ResponseEntity<Post> response = restTemplate.exchange(url, HttpMethod.POST, request, Post.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("use put to update a resource")
    void usePutToUpdateAResource() {
        String url = url("/posts/1");
        Post newPost = new Post("foo", "bar", 1L);
        restTemplate.put(url, newPost, Post.class);
    }

    @Test
    @DisplayName("use exchange to update a resource")
    void useExchangeToUpdateAResource() {
        String url = url("/posts/1");
        Post newPost = new Post("foo", "bar", 1L);
        HttpEntity<Post> request = new HttpEntity<>(newPost);
        ResponseEntity<Post> response = restTemplate.exchange(url, HttpMethod.PUT, request, Post.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("use patch to patch a resource ")
    @Disabled("Invalid HTTP method: PATCH")
    void usePatchToPatchAResource() {
        String url = url("/posts/1");
        Post newPost = new Post();
        newPost.setTitle("foo");
        Post patchedPost = restTemplate.patchForObject(url, newPost, Post.class);
        assertThat(patchedPost).hasNoNullFieldsOrProperties();
        assertThat(patchedPost).extracting(Post::getTitle).isEqualTo("foo");
    }

    @Test
    @DisplayName("use exchange to patch a resource")
    @Disabled("Invalid HTTP method: PATCH")
    void useExchangeToPatchAResource() {
        String url = url("/posts/1");
        Post newPost = new Post("foo", "bar", 1L);
        HttpEntity<Post> request = new HttpEntity<>(newPost);
        ResponseEntity<Post> response = restTemplate.exchange(url, HttpMethod.PATCH, request, Post.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasNoNullFieldsOrProperties();
        assertThat(response.getBody()).extracting(Post::getTitle).isEqualTo("foo");
    }

    @Test
    @DisplayName("delete a resource")
    @Disabled
    void deleteAResource() {
        String url = url("/posts/1");
        restTemplate.delete(url);
    }

    @Test
    @DisplayName("filter resources")
    void filterResources() {
        String url = url("/posts?userId=1");
        ResponseEntity<Post[]> response = restTemplate.getForEntity(url, Post[].class);
        List<Post> posts = Arrays.asList(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(posts).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("list nested resources")
    void listNestedResources() {
        String url = url("/posts/1/comments");
        ResponseEntity<Comment[]> response = restTemplate.getForEntity(url, Comment[].class);
        List<Comment> comments = Arrays.asList(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(comments).hasSizeGreaterThan(0);
    }

    
}
