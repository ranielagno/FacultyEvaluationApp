package raniel.evaluation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Student extends Activity{

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    Spinner spinner;
    Button submit, logout;
    String professor = "", username = "", name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            name = extras.getString("name");
        }

        spinner = (Spinner)findViewById(R.id.spinner);

        submit = (Button)findViewById(R.id.submit);
        logout = (Button)findViewById(R.id.logout);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(professor.isEmpty()){
                    Toast.makeText(Student.this, "Please select your professor.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(getApplicationContext(), Survey.class);
                    i.putExtra("professor", professor);
                    i.putExtra("username", username);
                    i.putExtra("name", name);
                    startActivity(i);
                    finish();
                }
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.professors, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                professor = adapterView.getSelectedItem().toString();

                if(professor.equals("Select Professor")){
                    professor = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
               professor = "";
            }
        });

    }
}
