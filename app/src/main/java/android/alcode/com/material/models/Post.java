package android.alcode.com.material.models;

/**
 * Created by MOMANI on 2016/03/22.
 */
public class Post {
    private String id;
    private String title;
    private String imageUrl;
    private String owner;
    private int Likes;

    public Post() {
    }

    public Post(String id, String title, String imageUrl, String owner, int likes) {

        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.owner = owner;
        Likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }
}
