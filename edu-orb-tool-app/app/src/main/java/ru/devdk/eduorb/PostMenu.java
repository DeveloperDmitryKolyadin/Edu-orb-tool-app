package ru.devdk.eduorb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PostMenu extends AppCompatActivity {


    private TextView x1_ssoTextView;
    private Button copyButton;
    private Button botButton;
    private Button clearSessionButton;
    private Button homeButton;
    private String x1_sso;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_menu);

        x1_sso = getIntent().getStringExtra("X1_SSO");

        x1_ssoTextView = findViewById(R.id.x1_sso_text_view);
        x1_ssoTextView.setText("Токен X1_SSO: " + x1_sso);

        copyButton = findViewById(R.id.copy_button);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("X1_SSO", x1_sso);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "Токен скопирован в буфер обмена", Toast.LENGTH_SHORT).show();
            }
        });

        botButton = findViewById(R.id.bot_button);
        botButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botUrl = "https://d5dgt0ncfq1n5n1sdjj7.apigw.yandexcloud.net/sso/" + x1_sso;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(botUrl));
                startActivity(browserIntent);
            }
        });

        clearSessionButton = findViewById(R.id.clear_session_button);
        clearSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookies(null);
                cookieManager.flush();
                Toast.makeText(getApplicationContext(), "Сессия очищена", Toast.LENGTH_SHORT).show();
            }
        });

        homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostMenu.this, base_menu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}