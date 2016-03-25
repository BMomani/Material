package android.alcode.com.material.models;

/**
 * Created by MOMANI on 2016/03/22.
 * this class for items in main activity
 */
public class Post {
    private String id;
    private String title;
    private String imageUrl;

    public Post() {
    }

    public Post(String id, String title, String imageUrl, String owner, int likes) {

        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
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
}
