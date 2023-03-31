package ru.devdk.eduorb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Objects;

public class LoginESIA extends AppCompatActivity {

    private WebView mWebView;
    private String mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_esia);

        // Получаем WebView по идентификатору
        mWebView = (WebView) findViewById(R.id.webview);

        // Включаем JavaScript в WebView
        mWebView.getSettings().setJavaScriptEnabled(true);

        // Создаем клиент для WebView
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Получаем куки после загрузки страницы
                CookieManager cookieManager = CookieManager.getInstance();
                String cookie = cookieManager.getCookie(url);
                if (cookie != null & Objects.equals(url, "https://edu.orb.ru/")) {
                    String[] parts = cookie.split(";");
                    for (String part : parts) {
                        String[] nameValue = part.split("=");
                        if (nameValue.length == 2 && "X1_SSO".equals(nameValue[0].trim())) {
                            mCode = nameValue[1].trim();
                            break;
                        }
                    }

                    // Если куки содержат X1_SSO, переходим на PostMenuActivity
                    Intent intent = new Intent(LoginESIA.this, PostMenu.class);
                    intent.putExtra("X1_SSO", mCode);
                    startActivity(intent);
                    finish(); // Закрываем текущую активити
                }
            }
        });

        // Загружаем страницу в WebView
        mWebView.loadUrl("https://edu.orb.ru/auth/rsaag/redirect/?returnTo=https://edu.orb.ru");
        
    }
}