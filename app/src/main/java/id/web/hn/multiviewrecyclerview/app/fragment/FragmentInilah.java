package id.web.hn.multiviewrecyclerview.app.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.concurrent.TimeUnit;

import id.web.hn.multiviewrecyclerview.R;
import id.web.hn.multiviewrecyclerview.app.adapter.FragmentInilahAdapter;
import id.web.hn.multiviewrecyclerview.app.api.BaseApi;
import id.web.hn.multiviewrecyclerview.app.model.NewsIkor;
import id.web.hn.multiviewrecyclerview.app.model.NewsModel;
import id.web.hn.multiviewrecyclerview.app.util.ConnectionDetector;
import id.web.hn.multiviewrecyclerview.app.util.EndlessRecyclerViewScrollListener;
import id.web.hn.multiviewrecyclerview.app.util.EndlessRecyclerViewScrollListener2;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hahn on 22/06/16.
 */
public class FragmentInilah extends Fragment {
    RecyclerView rvListMain;
    FragmentInilahAdapter mainAdapter;
    RealmList<NewsIkor> newsList = new RealmList<>();
    boolean isConnected = false;
    Realm myrealm;
    ProgressDialog mDialog;
    int page = 0, pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean loading;
    private static final int LIMIT = 10;
    private int totalpage = 10;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ConnectionDetector connectionDetector;

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());

//        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setHasOptionsMenu(true);

        rvListMain = (RecyclerView) rootView.findViewById(R.id.rvFragmentMain);
        mainAdapter = new FragmentInilahAdapter(getContext());

        rvListMain.setLayoutManager(mLinearLayoutManager);
        rvListMain.setAdapter(mainAdapter);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).build();

        //sumber: http://stackoverflow.com/a/34392556
        //
        try {
            myrealm = Realm.getInstance(realmConfig);
        } catch (RealmMigrationNeededException e){
            try {
                Realm.deleteRealm(realmConfig);
                //Realm file has been deleted.
                myrealm = Realm.getInstance(realmConfig);
            } catch (Exception ex){
                throw ex;
                //No Realm file to remove.
            }
        }

        connectionDetector = new ConnectionDetector(getContext());
        isConnected = connectionDetector.isInternetConnected();

        mDialog = new ProgressDialog(getContext());

        mainAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = mainAdapter.getNewsList().get(position).getLink();
                FragmentWebView fragmentWebView = new FragmentWebView();
                Bundle args = new Bundle();
                args.putString("url", url);
                fragmentWebView.setArguments(args);
                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, fragmentWebView)
                        .addToBackStack(null)
                        .commit();
            }
        });

//        rvListMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                Snackbar.make(getView(), "loading...", Snackbar.LENGTH_LONG).show();
//                visibleItemCount  = mLinearLayoutManager.getChildCount();
//                totalItemCount = mLinearLayoutManager.getItemCount();
//                pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();
//
////                Log.d("page loading: ", "page " + page
////                        + " visibleItemCount : " + visibleItemCount
////                        + " pastVisiblesItems:" + pastVisiblesItems
////                        + " totalItemCount: " + totalItemCount
////                        + " totalpage "  + totalpage );
//
//                if(((visibleItemCount + pastVisiblesItems) >= totalItemCount)){
//                    loading = true;
//                }
//
//                if(loading){
//                    if (((visibleItemCount + pastVisiblesItems) >= totalItemCount)
//                            && (totalItemCount >= LIMIT) && (totalpage >= page)){
//                        getDataFromAPI(true);
//                    }
//                } else {
//                    loading = false;
//                }
//            }
//        });

        /*
         * pakai endlessRecyclerViewScrollListener
         */
        rvListMain.addOnScrollListener(new EndlessRecyclerViewScrollListener2(mLinearLayoutManager) {
            @Override
            public int getFooterViewType(int defaultNoFooterViewType) {
                return 0;
            }

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
//                Snackbar.make(getView(),"Loading...", Snackbar.LENGTH_LONG).show();
                Log.d("RVScrollListener", "Page: " + page);
                getDataFromAPI(true);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_refresh){
//            Snackbar.make(getView(), "hoho", Snackbar.LENGTH_SHORT).show();
            Log.d("options", "option clik");
            onStart();
        }
        return true;
    }


    @Override
    public void onStart() {
        super.onStart();

        mDialog.show();
        if(isConnected == true){
            Log.d("onStart", "connected");
            if(mainAdapter.getNewsList().size() != 0){
                Log.d("onStart", "adapter berisi");
                mDialog.dismiss();
            }
            else{
                Log.d("onStart", "adapter kosong");
                //hapus dulu datanya
                RealmResults<NewsIkor> results = myrealm.where(NewsIkor.class).findAll();
                myrealm.beginTransaction();
                results.deleteAllFromRealm();
                myrealm.commitTransaction();

                getDataFromAPI(false);
            }

        }
        else{
            Log.d("onStart", "tak connect");
            Snackbar.make(getView(), "Tak bisa koneksi ke internet.", Snackbar.LENGTH_SHORT).show();
            if(mainAdapter.getNewsList().size() != 0){
                Log.d("onStart", "tak connect, berisi");
                mDialog.dismiss();
            }
            else {
                Log.d("onStart", "tak connect, kosong");
                getDataFromLocalDB();
            }
        }
    rvListMain.setAdapter(mainAdapter);
    }

    private void getDataFromLocalDB() {
        //TODO: implementasi ambil data dari realm

        mDialog.dismiss();
    }

    private void getDataFromAPI(boolean isLoadMore) {
        Log.d("getDataFromAPI", "page: " + page + "isLoadmore: " + isLoadMore);
        loading = false;
        FetchNewsIkorTask fetchNews = new FetchNewsIkorTask();
        fetchNews.execute(String.valueOf(page), String.valueOf(isLoadMore));
//        page += 1;

    }

    public class FetchNewsIkorTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            final String BASE_URL = "https://apiapikor.herokuapp.com";
            String APIKEY = "d42a9ad09e9778b177d409f5716ac621";
            int pageNews = Integer.parseInt(params[0]);
            boolean isLoadMore = Boolean.parseBoolean(params[1]);

            if(!isLoadMore){
                mainAdapter.getNewsList().clear();
            }
            pageNews++;
            Log.d("doInBackground", "pageNews: " + pageNews + " isLoadMore: " + isLoadMore);
            //TODO: perbaiki timeout di OkHttp
            //okhttp
            final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
            //retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //TODO: pilih call sesuai kategori atau pakai call untuk indeks

            BaseApi baseApi = retrofit.create(BaseApi.class);
            Call<NewsModel> call = baseApi.loadNewsPage(
                    pageNews
//                    ,APIKEY
            );
            call.enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    isiNewsList(response.body().getListNews());
                    page = response.body().getPage();
                    mDialog.dismiss();
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    Log.d("onFailure", "failure lur");
                    t.printStackTrace();
                    Snackbar.make(getView(), "network timeout", Snackbar.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            });


            return null;
        }

        private void isiNewsList(RealmList<NewsIkor> listNews) {
            if(listNews != null){
                for(NewsIkor n: listNews){
                    myrealm.beginTransaction();
                    myrealm.copyToRealmOrUpdate(n);
                    myrealm.commitTransaction();

                    mainAdapter.getNewsList().add(n);
                    mainAdapter.notifyDataSetChanged();
                }
            }
//            mDialog.dismiss();

        }
    }
}
