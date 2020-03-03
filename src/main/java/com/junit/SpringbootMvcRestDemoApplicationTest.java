package com.junit;


import com.domain.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootMvcRestDemoApplicationTest {

    private static final String ROOT_URL = "http://localhost:8080";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testGetAllPosts()
    {
        ResponseEntity<Post[]> responseEntity =
                restTemplate.getForEntity(ROOT_URL+"/posts", Post[].class);
        List<Post> posts = Arrays.asList(responseEntity.getBody());
        assertNotNull(posts);
    }
    @Test
    public void testGetPostById()
    {
        Post post = restTemplate.getForObject(ROOT_URL+"/posts/1", Post.class);
        assertNotNull(post);
    }
    @Test
    public void testCreatePost()
    {
        Post post = new Post();
        post.setTitle("Exploring SpringBoot REST");
        post.setContent("SpringBoot is awesome!!");
        post.setCreatedOn(new Date());
        ResponseEntity<Post> postResponse =
                restTemplate.postForEntity(ROOT_URL+"/posts", post, Post.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    @Test
    public void testUpdatePost()
    {
        int id = 1;
        Post post = restTemplate.getForObject(ROOT_URL+"/posts/"+id, Post.class);
        post.setContent("This my updated post1 content");
        post.setUpdatedOn(new Date());
        restTemplate.put(ROOT_URL+"/posts/"+id, post);
        Post updatedPost = restTemplate.getForObject(ROOT_URL+"/posts/"+id, Post.class);
        assertNotNull(updatedPost);
    }
    @Test
    public void testDeletePost()
    {
        int id = 2;
        Post post = restTemplate.getForObject(ROOT_URL+"/posts/"+id, Post.class);
        assertNotNull(post);
        restTemplate.delete(ROOT_URL+"/posts/"+id);
        try {
            post = restTemplate.getForObject(ROOT_URL+"/posts/"+id, Post.class);
        }
        catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}

}
