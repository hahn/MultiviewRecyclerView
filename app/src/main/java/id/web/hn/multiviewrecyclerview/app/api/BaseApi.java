package id.web.hn.multiviewrecyclerview.app.api;

import id.web.hn.multiviewrecyclerview.app.model.NewsIkor;
import id.web.hn.multiviewrecyclerview.app.model.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hahn on 22/06/16.
 */
public interface BaseApi {

    String paramNews = "/api/v1.0/news";
    String paramCat = "/api/v1.0/news/cat/{id}";
    String paramDetail = "/api/v1.0/news/{id}";
    String paramNewsPage = "/api/v1.0/news/page/{page}";

    @GET(paramNews)
    Call<NewsModel> loadNews(
            @Query("api_key") String API_KEY
    );

    @GET(paramCat)
    Call<NewsModel> loadNewsCat(
            @Path("id") int id,
            @Query("api_key") String API_KEY
    );

    @GET(paramNewsPage)
    Call<NewsModel> loadNewsPage(
            @Path("page") int page
//            ,@Query("api_key") String API_KEY
    );

    @GET(paramDetail)
    Call<NewsIkor> loadNewsDetail(
            @Path("id") int id,
            @Query("api_key") String API_KEY
    );


}
