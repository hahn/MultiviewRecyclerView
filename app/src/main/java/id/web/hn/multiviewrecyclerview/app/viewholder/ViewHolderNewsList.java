package id.web.hn.multiviewrecyclerview.app.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import id.web.hn.multiviewrecyclerview.R;

/**
 * Created by hahn on 19/06/16.
 */
public class ViewHolderNewsList extends RecyclerView.ViewHolder
        implements View.OnClickListener  {

    private ImageView imgList;
    private TextView txtTitleList, txtCatDateList;
    private int pos;
    private AdapterView.OnItemClickListener onItemClickListener;

    public ViewHolderNewsList(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imgList = (ImageView) itemView.findViewById(R.id.img_item_list);
        txtTitleList = (TextView) itemView.findViewById(R.id.txt_item_title);
        txtCatDateList = (TextView) itemView.findViewById(R.id.txt_item_cat);

    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }


    public TextView getTxtCatList() {
        return txtCatDateList;
    }

    public void setTxtCatList(TextView txtCatList) {
        this.txtCatDateList = txtCatList;
    }

    public TextView getTxtTitleList() {
        return txtTitleList;
    }

    public void setTxtTitleList(TextView txtTitleList) {
        this.txtTitleList = txtTitleList;
    }

    public ImageView getImgList() {
        return imgList;
    }

    public void setImgList(ImageView imgList) {
        this.imgList = imgList;
    }

    @Override
    public void onClick(View v) {

    }
}
