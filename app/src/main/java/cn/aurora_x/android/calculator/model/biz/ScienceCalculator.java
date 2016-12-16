package cn.aurora_x.android.calculator.model.biz;

import android.content.Context;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Rinko on 2016/11/15.
 */
public class ScienceCalculator {

    public static String del(String screen) {
        String newScreen = screen;
        if (screen.length() > 0) {
            if (screen.length() >= 4) {
                boolean delElement = false;
                final String[] elements = {"sin(", "cos(", "tan(", "sqrt("};
                for (String element : elements) {
                    int lastIndex = screen.lastIndexOf(element);
                    if (lastIndex != -1 && screen.substring(lastIndex).equals(element)) {
                        delElement = true;
                        newScreen = screen.substring(0, lastIndex);
                        break;
                    }
                }
                if (!delElement) {
                    newScreen = screen.substring(0, screen.length() - 1);
                }
            } else {
                newScreen = screen.substring(0, screen.length() - 1);
            }
        }
        return newScreen;
    }

    public static void calculate(String expression,Context context,final OnCalculateFinish listener) {
        final WebView webView=new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                if(message.equals("error")||message.equals("undefined")||message.equals("Infinity")){
                    listener.fail(message);
                }else {
                    listener.success(message);
                }
                result.cancel();
                return true;
            }
        });
        final String[] operations = {"sin(", "cos(", "tan(", "sqrt("};
        for (String element:operations){
            expression=expression.replace(element,"Math."+element);
        }
        final String exp=expression;
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                webView.loadUrl("javascript:alertEval("+exp+")");
            }
        });
        webView.loadUrl("file:///android_asset/alertEval.html");
    }
    public interface OnCalculateFinish{
        void success(String res);
        void fail(String msg);
    }
}
