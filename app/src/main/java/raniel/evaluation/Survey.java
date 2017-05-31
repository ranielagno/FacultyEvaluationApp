package raniel.evaluation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Survey extends Activity{

    LinearLayout linearLayout;
    Button submit, comment, previous;
    TextView question_number, question, or;
    RadioButton rbExcellent, rbAboveAverage, rbAverage, rbPoor, rbVeryPoor;

    ArrayList<RadioButton> rb  = new ArrayList<RadioButton>();
    RadioGroup radioGroup;
    int counter = 1;
    String professor = "", username = "", name = "", grade = "";
    String  saveGrade[] = new String[10];

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    CharSequence charSequence = "Welcome";
    String questions[] = {
            "Refrains from reading lectures.",
            "Discusses practical applications & values of the lesson.",
            "Answers students questions convincingly.",
            "Cite current development related to the lecture.",
            "Relates the lesson to other fields/discplines.",
            "Presents the objectives or the coverage of the day's lesson per syllables.",
            "Covers subject matter & provides learning experience.",
            "Presents lesson in orderly manner & practices harmonious transition from previous lesson.",
            "Summarizes the main points of the lesson.",
            "Explains all the points of the lesson clearly.",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        rb.add(rbVeryPoor = (RadioButton)findViewById(R.id.rbVeryPoor));
        rb.add(rbPoor = (RadioButton)findViewById(R.id.rbPoor));
        rb.add(rbAverage = (RadioButton)findViewById(R.id.rbAverage));
        rb.add(rbAboveAverage = (RadioButton)findViewById(R.id.rbAboveAverage));
        rb.add(rbExcellent = (RadioButton)findViewById(R.id.rbExcellent));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            professor = extras.getString("professor");
            username = extras.getString("username");
            name = extras.getString("name");
        }

        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        radioGroup = (RadioGroup)findViewById(R.id.rb);

        question_number = (TextView)findViewById(R.id.qNum);
        question = (TextView)findViewById(R.id.ask);
        or = (TextView)findViewById(R.id.or);

        question_number.setText("Question No. 1");
        question.setText(questions[0]);

        submit = (Button)findViewById(R.id.submit);
        comment = (Button)findViewById(R.id.comment);
        previous = (Button)findViewById(R.id.previous);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioGroup.clearCheck();

                if(!grade.equals("")){

                    if(counter==10){

                        saveGrade[counter - 1] = grade;

                        BackgroundWorker backgroundWorker = new BackgroundWorker(Survey.this);

                        if(name.contains(charSequence))
                            name = name.substring(8,name.length());

                        Log.d("Name" , name);
                        backgroundWorker.execute("Save", name, professor, saveGrade[0] ,saveGrade[1] ,saveGrade[2] ,saveGrade[3] ,saveGrade[4] ,saveGrade[5] ,saveGrade[6] ,saveGrade[7] ,saveGrade[8] ,saveGrade[9], "", "");

                    }else {

                        if(counter>0) {
                            previous.setVisibility(View.VISIBLE);
                            /*
                            if(saveGrade[counter-1]!=null) {
                                grade = saveGrade[counter-1];
                                rb.get(Integer.parseInt(saveGrade[counter]) - 1).setChecked(true);

                            }*/

                        }
                        saveGrade[counter - 1] = grade;

                        question.setText(questions[counter]);
                        ++counter;
                        question_number.setText("Question No. " + counter);
                        grade = "";
                        if(counter==10) {
                            submit.setText("Submit");
                            comment.setVisibility(View.VISIBLE);
                            or.setVisibility(View.VISIBLE);
                        }
                    }
                }else{
                    Toast.makeText(Survey.this, "Please rate the professor based on the question", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                --counter;
                question.setText(questions[counter-1]);
                question_number.setText("Question No. " + counter);

                grade = saveGrade[counter-1];
                rb.get(Integer.parseInt(grade)-1).setChecked(true);

                if(counter==1)
                    previous.setVisibility(View.INVISIBLE);
                else if(counter<10){
                    submit.setText("Next");
                    comment.setVisibility(View.INVISIBLE);
                    or.setVisibility(View.INVISIBLE);
                }
                linearLayout.setGravity(Gravity.CENTER);
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!grade.equals("")) {
                    saveGrade[counter - 1] = grade;
                    if (name.contains(charSequence))
                        name = name.substring(8, name.length());

                    Intent intent = new Intent(getApplicationContext(), Comment.class);
                    intent.putExtra("grade", saveGrade);
                    intent.putExtra("name", name);
                    intent.putExtra("professor", professor);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Survey.this, "Please rate the professor based on the question", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbExcellent:
                if (checked)
                     grade = "5";
                break;
            case R.id.rbAboveAverage:
                if (checked)
                    grade = "4";
                break;
            case R.id.rbAverage:
                if (checked)
                    grade = "3";
                break;
            case R.id.rbPoor:
                if (checked)
                    grade = "2";
                break;
            case R.id.rbVeryPoor:
                if (checked)
                    grade = "1";
                break;
        }
    }
}
