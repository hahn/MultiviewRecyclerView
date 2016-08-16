package id.web.hn.multiviewrecyclerview.app.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hahn on 19/06/16.
 */
public class NewsModel extends RealmObject implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private RealmList<NewsIkor> listNews = new RealmList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public RealmList<NewsIkor> getListNews() {
        return listNews;
    }

    public void setListNews(RealmList<NewsIkor> listNews) {
        this.listNews = listNews;
    }
}
