package comt.example.administrator.gogogo;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Author: zwf(zhaoweifeng@1000phone.com)
 * Date  : 2017-01-08
 * Time  : 17:35
 * Project: gogogo
 * Descrite:
 */

public class MyJavaScript {

    private Context context;

    public MyJavaScript(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String content){

        Toast.makeText(context,content, Toast.LENGTH_SHORT).show();

    }

   @JavascriptInterface
    public String getData(){

        return "三星轰炸机";
    }
}
