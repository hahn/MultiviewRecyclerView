package id.web.hn.multiviewrecyclerview.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hahn on 22/06/16.
 */
public class NewsIkor extends RealmObject implements Serializable {

    @SerializedName("judul")
    @Expose
    private String judul;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("kategori")
    @Expose
    private String kategori;

    @SerializedName("id")
    @Expose
    private int id;

    @PrimaryKey
    @SerializedName("url_id")
    @Expose
    private String urlId;

    @SerializedName("kategori_id")
    @Expose
    private String kategori_id;

    @SerializedName("penulis")
    @Expose
    private String penulis;

    @SerializedName("waktu")
    @Expose
    private String waktu;

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id = kategori_id;
    }
}
