package cn.xdevops.springboot.examples.webclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String body;
    private Long userId;

    public Post(String title, String body, Long userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
