package raniel.evaluation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Evaluation extends Activity{

    LinearLayout linearLayout;
    TextView prof;
    double ave = 0 ;
    String professor;
    String gcomment[], bcomment[];
    String score[] = {"No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation"};
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            professor = extras.getString("professor");
            score = extras.getStringArray("score");
            gcomment = extras.getStringArray("gcomment");
            bcomment = extras.getStringArray("bcomment");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        prof = (TextView)findViewById(R.id.prof);
        prof.setText("Professor "+professor);
        List<TextView> textList = new ArrayList<TextView>();
        DecimalFormat df = new DecimalFormat("###.##");
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        /*
        for(int x=0;x<questions.length; x++){

            TextView textView = new TextView(this);
            textView.setTextSize(30);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5, 30, 5, 15);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER);
            textView.setText("Question No. "+(x+1));
            textView.setTextColor(Color.WHITE);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
            linearLayout.addView(textView);

            TextView question = new TextView(this);
            question.setTextSize(20);
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp1.setMargins(15, 5, 15, 5);
            question.setLayoutParams(lp1);
            question.setGravity(Gravity.CENTER);
            question.setText(questions[x]);
            question.setTextColor(Color.WHITE);
            question.setTypeface(null, Typeface.BOLD);
            question.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
            linearLayout.addView(question);
            //textList.add(textView);

            TextView grade = new TextView(this);
            grade.setTextSize(20);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp2.setMargins(5, 10, 5, 20);
            grade.setLayoutParams(lp2);
            grade.setGravity(Gravity.CENTER);

            if(!score[x].equals("No Evaluation"))
                grade.setText("Grade: "+df.format(Double.parseDouble(score[x])));
            else
                grade.setText("Grade: No Evaluation");

            grade.setTextColor(Color.WHITE);
            grade.setTypeface(null, Typeface.BOLD);
            grade.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
            linearLayout.addView(grade);

        }*/
            TextView average = new TextView(this);
            average.setTextSize(35);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5, 30, 5, 15);
            average.setLayoutParams(lp);
            average.setGravity(Gravity.CENTER);
            average.setTextColor(Color.WHITE);
            average.setTypeface(null, Typeface.BOLD);
            average.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

            for(int z=0; z<10; z++){
                if(!score[z].equals("No Evaluation"))
                    ave += Double.parseDouble(score[z]);
            }
            ave /= 10;
            average.setText("Total Average Grade: "+df.format(ave));
            linearLayout.addView(average);

            TextView commenthead = new TextView(this);
            commenthead.setTextSize(35);
            lp.setMargins(5, 30, 5, 15);
            commenthead.setLayoutParams(lp);
            commenthead.setGravity(Gravity.CENTER);
            commenthead.setTextColor(Color.WHITE);
            commenthead.setTypeface(null, Typeface.BOLD);
            commenthead.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
            linearLayout.addView(commenthead);

            boolean checker = false;
            int counter = 0;

            if(gcomment!=null) {
                for (int y = 1; y < gcomment.length; y++) {

                    if (!gcomment[y].trim().equals("")) {
                        checker = true;
                        TextView comments = new TextView(this);
                        comments.setTextSize(25);
                        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp1.setMargins(40, 5, 40, 5);
                        comments.setLayoutParams(lp1);
                        comments.setGravity(Gravity.CENTER);
                        comments.setText((++counter) + ". " + gcomment[y].trim());
                        comments.setTextColor(Color.WHITE);
                        comments.setTypeface(null, Typeface.BOLD);
                        comments.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
                        linearLayout.addView(comments);
                    }
                }
            }
            if(checker)
                commenthead.setText("Good Comments");

            TextView commenthead2 = new TextView(this);
            commenthead2.setTextSize(35);
            lp.setMargins(5, 30, 5, 15);
            commenthead2.setLayoutParams(lp);
            commenthead2.setGravity(Gravity.CENTER);
            commenthead2.setTextColor(Color.WHITE);
            commenthead2.setTypeface(null, Typeface.BOLD);
            commenthead2.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
            linearLayout.addView(commenthead2);

            boolean checker2 = false;
            counter = 0;

            if(bcomment!=null) {
                for (int y = 1; y < bcomment.length; y++) {
                    if (!bcomment[y].trim().equals("")) {
                        checker2 = true;
                        TextView comments2 = new TextView(this);
                        comments2.setTextSize(25);
                        LinearLayout.LayoutParams lp12 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp12.setMargins(40, 5, 40, 5);
                        comments2.setLayoutParams(lp12);
                        comments2.setGravity(Gravity.CENTER);
                        comments2.setText((++counter) + ". " + bcomment[y].trim());
                        comments2.setTextColor(Color.WHITE);
                        comments2.setTypeface(null, Typeface.BOLD);
                        comments2.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
                        linearLayout.addView(comments2);
                    }
                }
            }
            if(checker2)
                commenthead2.setText("Bad Comments");


            Button logout = new Button(this);
            logout.setTextSize(25);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp2.setMargins(40, 20, 40, 40);
            logout.setLayoutParams(lp2);
            logout.setGravity(Gravity.CENTER);
            logout.setText("Logout");
            logout.setTextColor(Color.WHITE);
            logout.setBackgroundResource(R.color.maroon);
            linearLayout.addView(logout);

            logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
                }
        });
    }
}
