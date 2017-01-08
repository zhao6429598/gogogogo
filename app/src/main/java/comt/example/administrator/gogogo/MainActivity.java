package comt.example.administrator.gogogo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView imageView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        imageView = (ImageView) findViewById(R.id.image);

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
//        settings.setBuiltInZoomControls(true);
        settings.setGeolocationEnabled(true);
        settings.setSupportZoom(true);

        webView.setWebViewClient(new MyClient());
        webView.setWebChromeClient(new MyChrome());

        webView.addJavascriptInterface(new MyJavaScript(this),"zhao");

//      webView.loadUrl("http://www.baidu.com");
        webView.loadUrl("file:///android_asset/index.html");
    }



    public void btnClick(View view) {
      //TODO 调用javascript
        webView.loadUrl("javascript:show('android调用js代码!')");

    }


    public class MyChrome extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Log.e(TAG, "onProgressChanged: "+newProgress );
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.e(TAG, "onReceivedTitle: "+title );
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            imageView.setImageBitmap(icon);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);

            Log.e(TAG, "onReceivedTouchIconUrl: precomposed:"+precomposed+" url:"+url );

        }
    }



    //WebViewClient
    public  class MyClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e(TAG, "onPageStarted: "+url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {

            Log.e(TAG, "shouldOverrideUrlLoading: "+request);

            return super.shouldOverrideUrlLoading(view, request);
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG, "onPageFinished: "+url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.e(TAG, "onLoadResource: "+url);
        }
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
        {
            webView.goBack();
        }else {
            finish();
        }

    }
}
