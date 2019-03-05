package db;

import domain.BlogPost;

import java.util.Map;

public interface BlogRepository {

    void add(BlogPost blogPost);

    void delete(Integer blogPostId);

    BlogPost get(Integer blogPostId);

    Map<Integer, BlogPost> getAll();
}
