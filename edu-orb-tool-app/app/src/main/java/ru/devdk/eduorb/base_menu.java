package ru.devdk.eduorb;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.devdk.eduorb.databinding.ActivityBaseMenuBinding;

public class base_menu extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityBaseMenuBinding binding;

    private Button buttonLoginGov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_menu);

        buttonLoginGov = findViewById(R.id.button_login_gov);
        buttonLoginGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(base_menu.this, LoginESIA.class);
                startActivity(intent);
            }
        });
    }
}