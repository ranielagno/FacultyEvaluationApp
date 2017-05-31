package raniel.evaluation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Thread timer = new Thread(){

            public void run(){
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openMain = new Intent(getApplicationContext(), Login.class);
                    startActivity(openMain);
                    finish();
                }
            }

        };
        timer.start();
    }

}
