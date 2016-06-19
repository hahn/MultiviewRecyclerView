package id.web.hn.multiviewrecyclerview.app.model;

import java.io.Serializable;

/**
 * Created by hahn on 19/06/16.
 */
public class News implements Serializable {
    private String title, teaser, category, date, urlImage;

    public News(String title, String teaser, String category, String date, String urlImage) {
        this.title = title;
        this.teaser = teaser;
        this.category = category;
        this.date = date;
        this.urlImage = urlImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
