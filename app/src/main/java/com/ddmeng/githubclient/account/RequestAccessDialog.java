package com.ddmeng.githubclient.account;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ddmeng.githubclient.BuildConfig;
import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("SetJavaScriptEnabled")
public class RequestAccessDialog extends DialogFragment {
    public static final String TAG = RequestAccessDialog.class.getSimpleName();

    private static final String URL_REQUEST_ACCESS = "https://github.com/login/oauth/authorize";
    private static final String CLIENT_ID = "CLIENT_ID";
    private static final String REDIRECT_URI = "REDIRECT_URI";
    private static final String SCOPE = "SCOPE";

    @BindView(R.id.webview)
    WebView webView;

    public static RequestAccessDialog show(String clientId, String redirectUri) {
        return getInstance(clientId, redirectUri, null);
    }

    public static RequestAccessDialog getInstance(String clientId, String redirectUri, String scope) {

        RequestAccessDialog dialogFragment = new RequestAccessDialog();
        Bundle args = new Bundle();
        args.putString(CLIENT_ID, clientId);
        args.putString(REDIRECT_URI, redirectUri);
        args.putString(SCOPE, scope);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AppCompatDialog(getActivity());
        dialog.setContentView(R.layout.dialog_request_access);
        ButterKnife.bind(this, dialog);
        initWebView();
        return dialog;
    }

    private void initWebView() {
        webView.setWebViewClient(new OAuthWebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(setUpUrl());
    }

    private String setUpUrl() {
        Bundle arguments = getArguments();
        String clientId = arguments.getString(CLIENT_ID);
        String redirectUri = arguments.getString(REDIRECT_URI);
        String scope = arguments.getString(SCOPE);
        String url = URL_REQUEST_ACCESS + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&scope=" + scope;
        LogUtils.i(TAG, url);
        return url;
    }

    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.d(TAG, "Redirecting URL " + url);

            if (url.startsWith(BuildConfig.CALLBACK_URL)) {
                String urls[] = url.split("=");
                LogUtils.i(TAG, "code: " + urls[1]);
//                mListener.onComplete(urls[1]);
//                RequestAccessDialog.this.dismiss();
                return false;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
