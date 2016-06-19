package id.web.hn.multiviewrecyclerview.app.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import id.web.hn.multiviewrecyclerview.R;

/**
 * Created by hahn on 19/06/16.
 * holder buat tampilan yang ada image gedenya
 */
public class ViewHolderNewsImage extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private ImageView imgFront;
    private TextView txtTitle, txtTeaser, txtCat, txtDate;
    private int pos;
    private AdapterView.OnItemClickListener onItemClickListener;

    public ViewHolderNewsImage(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imgFront = (ImageView) itemView.findViewById(R.id.img_item_rv);
        txtTitle = (TextView) itemView.findViewById(R.id.txt_item_img_title);
        txtTeaser = (TextView) itemView.findViewById(R.id.txt_item_img_teaser);
        txtCat = (TextView) itemView.findViewById(R.id.txt_item_img_catdate);
        txtDate = (TextView) itemView.findViewById(R.id.txt_item_img_catdate);
    }

    public ImageView getImgFront() {
        return imgFront;
    }

    public void setImgFront(ImageView imgFront) {
        this.imgFront = imgFront;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(TextView txtTitle) {
        this.txtTitle = txtTitle;
    }

    public TextView getTxtTeaser() {
        return txtTeaser;
    }

    public void setTxtTeaser(TextView txtTeaser) {
        this.txtTeaser = txtTeaser;
    }

    public TextView getTxtCat() {
        return txtCat;
    }

    public void setTxtCat(TextView txtCat) {
        this.txtCat = txtCat;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(TextView txtDate) {
        this.txtDate = txtDate;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {

    }
}
