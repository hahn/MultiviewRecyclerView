package id.web.hn.multiviewrecyclerview.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import id.web.hn.multiviewrecyclerview.R;
import id.web.hn.multiviewrecyclerview.app.model.NewsIkor;
import id.web.hn.multiviewrecyclerview.app.viewholder.ViewHolderNewsImage;
import id.web.hn.multiviewrecyclerview.app.viewholder.ViewHolderNewsList;
import io.realm.RealmList;

/**
 * Created by hahn on 22/06/16.
 */
public class FragmentInilahAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmList<NewsIkor> newsList;
    private Context ctx;
    private final int NEWS_IMAGE = 0, NEWS_LIST = 1;
    private AdapterView.OnItemClickListener onItemClickListener;

    public FragmentInilahAdapter(Context ctx) {
        this.ctx = ctx;
        this.newsList = new RealmList<>();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RealmList<NewsIkor> getNewsList(){
        return newsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == 0){
            View vImage = inflater.inflate(R.layout.item_rv_img, parent, false);
            holder = new ViewHolderNewsImage(vImage);
        }
        else {
            View vList = inflater.inflate(R.layout.item_rv_list, parent, false);
            holder = new ViewHolderNewsList(vList);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == NEWS_IMAGE){
            ViewHolderNewsImage holderNewsImage = (ViewHolderNewsImage) holder;
            configViewImage(holderNewsImage, position);
        }
        else {
            ViewHolderNewsList holderNewsList = (ViewHolderNewsList) holder;
            configViewList(holderNewsList, position);
        }
    }

    private void configViewList(ViewHolderNewsList holderNewsList, int position) {
        NewsIkor news = newsList.get(position);
        String urlImage;
        String catDateWriter;
        if(news != null){
            urlImage = news.getImg();
            catDateWriter = /*news.getKategori() + " | " + */  news.getPenulis() + " | " + news.getWaktu();
            holderNewsList.getTxtTitleList().setText(news.getJudul());
            holderNewsList.getTxtCatList().setText(catDateWriter);
            holderNewsList.getTxtCatList().setAllCaps(true);
            holderNewsList.setPos(position);

            //image
            Picasso picasso = Picasso.with(ctx);
            if(urlImage.isEmpty()){
//                Log.d("configView", "img empty");
                picasso.load(R.drawable.ikor2)
                        .into(holderNewsList.getImgList());
            }else{
                picasso.load(urlImage)
                        .error(R.drawable.ikor2)
                        .into(holderNewsList.getImgList());
            }

        }

        holderNewsList.setOnItemClickListener(onItemClickListener);
    }

    private void configViewImage(ViewHolderNewsImage holderNewsImage, int position) {
        NewsIkor news = newsList.get(position);
        String urlImage;
        String catDateWriter;
        if(news != null){
            urlImage = news.getImg();
            catDateWriter = /*news.getKategori() + " | " + */ news.getPenulis() + " | " + news.getWaktu();

            holderNewsImage.getTxtTitle().setText(news.getJudul());
            holderNewsImage.getTxtCat().setText(catDateWriter);
            holderNewsImage.getTxtCat().setAllCaps(true);
            //TODO: Teaser ganti sama awal naskah!
            holderNewsImage.getTxtTeaser().setText(news.getJudul());
            holderNewsImage.setPos(position);

            //TODO: perbaiki ketika src gambar kosong (masih error). FIXED

            //image
            Picasso picasso = Picasso.with(ctx);
            if(urlImage.isEmpty()){
                Log.d("configView", "img empty");
                picasso.load(R.drawable.ikor2)
                        .into(holderNewsImage.getImgFront());
            }else{
                picasso.load(urlImage)
                        .error(R.drawable.ikor2)
                        .into(holderNewsImage.getImgFront());
            }
        }
        holderNewsImage.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 4 == 0) ? NEWS_IMAGE : NEWS_LIST;
    }
}
