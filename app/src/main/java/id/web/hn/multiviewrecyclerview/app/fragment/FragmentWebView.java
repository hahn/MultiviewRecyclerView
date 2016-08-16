package id.web.hn.multiviewrecyclerview.app.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import id.web.hn.multiviewrecyclerview.R;
import id.web.hn.multiviewrecyclerview.activity.MainActivity;

/**
 * Created by hahn on 7/4/16.
 */
public class FragmentWebView extends Fragment {

    WebView webview;

    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_webview, container,false);

//        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setHasOptionsMenu(true);

        url = getArguments().getString("url");
        webview = (WebView) rootview.findViewById(R.id.webview);
        webView();
        return rootview;
    }

    private void webView() {
        WebSettings mwWebSettings = webview.getSettings();
        mwWebSettings.setJavaScriptEnabled(true);

//        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("inilahkoran.com")) { //Force to open the url in WEBVIEW
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}
