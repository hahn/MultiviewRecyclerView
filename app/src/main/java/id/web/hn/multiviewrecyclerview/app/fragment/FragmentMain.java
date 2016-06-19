package id.web.hn.multiviewrecyclerview.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.web.hn.multiviewrecyclerview.R;
import id.web.hn.multiviewrecyclerview.app.adapter.FragmentMainAdapter;
import id.web.hn.multiviewrecyclerview.app.model.News;

public class FragmentMain extends Fragment {
    RecyclerView rvListMain;
    FragmentMainAdapter mainAdapter;
    ArrayList<News> newsList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());

        rvListMain = (RecyclerView) rootView.findViewById(R.id.rvFragmentMain);
        mainAdapter = new FragmentMainAdapter(getContext());

        rvListMain.setLayoutManager(mLinearLayoutManager);
        insertDummy();

        mainAdapter.getNewsList().addAll(newsList);
        rvListMain.setAdapter(mainAdapter);

        return rootView;
    }

    public void insertDummy(){
        int jml = 20;
        News news;
        for (int i = 0; i < jml; i++) {
            news = new News("Judul yang panjang sekali pisan loh masa sih iya " + i, "", "category", (i+1) + "-04-2016","img.jpg");
            newsList.add(news);
        }
    }
}
