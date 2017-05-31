package raniel.evaluation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Login extends Activity {

    EditText UsernameEt, PasswordEt;
    String type;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UsernameEt = (EditText)findViewById(R.id.etUsername);
        PasswordEt = (EditText)findViewById(R.id.etPassword);

    }

    public void onLogin(View view){

        final ProgressDialog dialog = ProgressDialog.show(Login.this, "",
                "Loading. Please wait...", true);
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);

        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        type = "Login";
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter username or password.", Toast.LENGTH_SHORT).show();
        }else {

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);
        }
    }

    public void onRegister(View view){

        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }
}

