package raniel.evaluation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Comment extends Activity {

    Button submit;
    EditText gcomment, bcomment;

    String professor = "", name = "";
    String  grade[] = new String[10];

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            professor = extras.getString("professor");
            grade = extras.getStringArray("grade");
            name = extras.getString("name");
        }

        submit = (Button)findViewById(R.id.submit);
        gcomment = (EditText)findViewById(R.id.gcomment);
        bcomment = (EditText)findViewById(R.id.bcomment);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(Comment.this);
                backgroundWorker.execute("Save", name, professor, grade[0] ,grade[1] ,grade[2] ,grade[3] ,grade[4] ,grade[5] ,grade[6] ,grade[7] ,grade[8] ,grade[9], gcomment.getText().toString(), bcomment.getText().toString());
            }
        });
    }
}
