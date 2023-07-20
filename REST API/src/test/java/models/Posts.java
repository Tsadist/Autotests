package models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Posts {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posts posts = (Posts) obj;
        return Objects.equals(userId, posts.userId)
                || Objects.nonNull(posts.id) && Objects.isNull(id)
                || Objects.nonNull(id) && Objects.isNull(posts.id)
                || Objects.equals(id, posts.id)
                && Objects.equals(title, posts.title)
                && Objects.equals(body, posts.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }
}
