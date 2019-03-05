package db;

import domain.BlogPost;
import domain.Comment;

import java.util.HashMap;
import java.util.Map;

public class BlogRepositoryStub implements BlogRepository {
    private Map<Integer, BlogPost> blogposts = new HashMap<>();
    private Integer idCounter = 0;

    public BlogRepositoryStub() {
        BlogPost post = new BlogPost("Was het een interessante projectweek?");
        post.addComment(new Comment("Elke", "Nee. Super saai!", 2));
        add(post);
        add(new BlogPost("Wat ben je van plan om te doen vandaag?"));
        add(new BlogPost("Naar welke muziek ben je momenteel aan het luisteren?"));
        add(new BlogPost("Wat zijn de examenvragen voor het vak Web4?"));
        add(new BlogPost("Is dit project goed afgewerkt?"));
    }

    public BlogPost get(Integer blogPostId) {
        BlogPost found = blogposts.get(blogPostId);
        if (found == null)
            throw new IllegalArgumentException("BlogPost with id " + blogPostId + " does not exist");

        return found;
    }

    public Map<Integer, BlogPost> getAll() {
        return blogposts;
    }

    public void add(BlogPost blogPost) {
        if (blogPost == null) {
            throw new IllegalArgumentException("No blogpost given");
        }

        for (Comment comment : blogPost.getComments()) {
            comment.setBlogId(idCounter);
        }

        blogposts.put(idCounter, blogPost);
        idCounter++;
    }

    public void delete(Integer blogPostId) {
        //noinspection SuspiciousMethodCalls
        blogposts.remove(get(blogPostId));
    }
}
