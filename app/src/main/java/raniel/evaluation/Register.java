package raniel.evaluation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class Register extends Activity{
    String username = "",password = "",firstname = "",middlename = "",lastname = "",position = "Student", course = "", section = "";
    EditText UsernameET, PasswordET, FirstnameET, MiddlenameET, LastnameET, CourseET, SectionET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirstnameET = (EditText)findViewById(R.id.FirstName);
        MiddlenameET = (EditText)findViewById(R.id.MiddleName);
        LastnameET = (EditText)findViewById(R.id.LastName);
        UsernameET = (EditText)findViewById(R.id.etSetUsername);
        PasswordET = (EditText)findViewById(R.id.etSetPassword);
        CourseET = (EditText)findViewById(R.id.etSetCourse);
        SectionET = (EditText)findViewById(R.id.etSetSection);
    }

    public void onSignUp(View view){
        username = UsernameET.getText().toString();
        password = PasswordET.getText().toString();
        firstname = FirstnameET.getText().toString();
        middlename = MiddlenameET.getText().toString();
        lastname = LastnameET.getText().toString();
        course = CourseET.getText().toString();
        section = SectionET.getText().toString();

        if(firstname.isEmpty()){
            Toast.makeText(this, "Please enter first name.", Toast.LENGTH_SHORT).show();
        }else if(middlename.isEmpty()){
            Toast.makeText(this, "Please enter middle name.", Toast.LENGTH_SHORT).show();
        }else if(lastname.isEmpty()){
            Toast.makeText(this, "Please enter last name.", Toast.LENGTH_SHORT).show();
        }else if(username.isEmpty()){
            Toast.makeText(this, "Please enter username.", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show();
        }/*else if(position.isEmpty()){
            Toast.makeText(this, "Please select position.", Toast.LENGTH_SHORT).show();
        }*/else if(course.isEmpty()){
            Toast.makeText(this, "Please enter course.", Toast.LENGTH_SHORT).show();
        }else if(section.isEmpty()){
            Toast.makeText(this, "Please enter section.", Toast.LENGTH_SHORT).show();
        }else {
            String type = "Register";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, firstname, middlename, lastname, username, password, position, course, section);
        }
    }
    /*
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbAdmin:
                if (checked)
                    position = "Admin";
                    break;
            case R.id.rbStudent:
                if (checked)
                    position = "Student";
                    break;
        }
    }*/



}
