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

public class Admin extends Activity {

    Spinner spinner;
    Button submit, logout;
    String professor = "", username = "", name = "";


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            name = extras.getString("name");
        }

        spinner = (Spinner)findViewById(R.id.spinner);

        submit = (Button)findViewById(R.id.submit);
        //logout = (Button)findViewById(R.id.logout);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(professor.isEmpty()){
                    Toast.makeText(Admin.this, "Please select a professor.", Toast.LENGTH_SHORT).show();
                }else {
                    BackgroundWorker backgroundWorker = new BackgroundWorker(Admin.this);
                    backgroundWorker.execute("Get",professor);
                }
            }
        });
        /*
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });*/

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
