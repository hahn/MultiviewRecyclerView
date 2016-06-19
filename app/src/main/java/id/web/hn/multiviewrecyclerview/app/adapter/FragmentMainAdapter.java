package id.web.hn.multiviewrecyclerview.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import id.web.hn.multiviewrecyclerview.R;
import id.web.hn.multiviewrecyclerview.app.model.News;
import id.web.hn.multiviewrecyclerview.app.viewholder.ViewHolderNewsImage;
import id.web.hn.multiviewrecyclerview.app.viewholder.ViewHolderNewsList;

/**
 * Created by hahn on 19/06/16.
 */
public class FragmentMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<News> newsList;
    private Context ctx;
    private final int NEWS_IMAGE = 0, NEWS_LIST = 1;
    private AdapterView.OnItemClickListener onItemClickListener;

    public FragmentMainAdapter(Context ctx) {
        this.ctx = ctx;
        this.newsList = new ArrayList<>();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ArrayList<News> getNewsList() {
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

    /*
     * memilih viewholder yang bersesuaian
     */
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
        News news = newsList.get(position);
        if(news != null){
            holderNewsList.getTxtTitleList().setText(news.getTitle());
            holderNewsList.getTxtCatList().setText(news.getCategory() + " | " + news.getDate());
            holderNewsList.getImgList().setImageResource(R.drawable.img); //nantinya harusnya pake url

        }
    }

    private void configViewImage(ViewHolderNewsImage holderNewsImage, int position) {
        News news = newsList.get(position);
        holderNewsImage.getTxtTitle().setText(news.getTitle());
        holderNewsImage.getTxtTeaser().setText(R.string.tmp_teaser);
        holderNewsImage.getTxtCat().setText(news.getCategory() + " | " + news.getDate());
        holderNewsImage.getImgFront().setImageResource(R.drawable.img); //dummy

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 5 == 0) ? NEWS_IMAGE : NEWS_LIST;
    }
}
