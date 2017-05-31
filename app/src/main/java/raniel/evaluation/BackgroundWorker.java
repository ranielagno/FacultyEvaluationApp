package raniel.evaluation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Intent.getIntent;
import static java.lang.Thread.sleep;


public class BackgroundWorker extends AsyncTask<String, Void, String>{

    Context context;
    AlertDialog alertDialog;
    String type, strURL, username = "", name = "", professor = "";
    String score[] = {"No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation","No Evaluation"};
    String two[] = new String[2];
    String twoName[] = new String[2];
    //ArrayList<String> comment = new ArrayList<String>();
    String comment[], gcomment[], bcomment[];

    BackgroundWorker(Context ctx) {

        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        type = strings[0];
        String result = connect(strings);
        return result;
    }

    private String connect(String[] strings) {
        String post_data = "";

        try {
            if (type.equals("Login")) {
                strURL = "http://evaluation.esy.es/login.php";
                post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(strings[2], "UTF-8");;
                username = strings[1];
            }else if(type.equals("Register")) {
                strURL = "http://evaluation.esy.es/register.php";
                post_data = URLEncoder.encode("FirstName","UTF-8")+"="+URLEncoder.encode(strings[1],"UTF-8")+"&"+ URLEncoder.encode("MiddleName","UTF-8")+"="+URLEncoder.encode(strings[2],"UTF-8")+"&"+ URLEncoder.encode("LastName","UTF-8")+"="+URLEncoder.encode(strings[3],"UTF-8")+"&"+URLEncoder.encode("Username","UTF-8")+"="+URLEncoder.encode(strings[4],"UTF-8")+"&"+URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(strings[5],"UTF-8") +"&"+ URLEncoder.encode("Position","UTF-8")+"="+URLEncoder.encode(strings[6],"UTF-8") +"&"+ URLEncoder.encode("Course","UTF-8")+"="+URLEncoder.encode(strings[7],"UTF-8")  +"&"+ URLEncoder.encode("Section","UTF-8")+"="+URLEncoder.encode(strings[8],"UTF-8");
            }else if(type.equals("Save")){
                name = strings[1];
                strURL = "http://evaluation.esy.es/save.php";
                post_data = URLEncoder.encode("Evaluator","UTF-8")+"="+URLEncoder.encode(strings[1],"UTF-8")+"&"+ URLEncoder.encode("Professor","UTF-8")+"="+URLEncoder.encode(strings[2],"UTF-8")+"&"+ URLEncoder.encode("q1","UTF-8")+"="+URLEncoder.encode(strings[3],"UTF-8")+"&"+URLEncoder.encode("q2","UTF-8")+"="+URLEncoder.encode(strings[4],"UTF-8")+"&"+URLEncoder.encode("q3","UTF-8")+"="+URLEncoder.encode(strings[5],"UTF-8") +"&"+ URLEncoder.encode("q4","UTF-8")+"="+URLEncoder.encode(strings[6],"UTF-8") +"&"+ URLEncoder.encode("q5","UTF-8")+"="+URLEncoder.encode(strings[7],"UTF-8") +"&"+ URLEncoder.encode("q6","UTF-8")+"="+URLEncoder.encode(strings[8],"UTF-8") +"&"+ URLEncoder.encode("q7","UTF-8")+"="+URLEncoder.encode(strings[9],"UTF-8") +"&"+ URLEncoder.encode("q8","UTF-8")+"="+URLEncoder.encode(strings[10],"UTF-8") +"&"+ URLEncoder.encode("q9","UTF-8")+"="+URLEncoder.encode(strings[11],"UTF-8") +"&"+ URLEncoder.encode("q10","UTF-8")+"="+URLEncoder.encode(strings[12],"UTF-8")+"&"+ URLEncoder.encode("GoodComment","UTF-8")+"="+URLEncoder.encode(strings[13],"UTF-8")+"&"+ URLEncoder.encode("BadComment","UTF-8")+"="+URLEncoder.encode(strings[14],"UTF-8");
            }else if(type.equals("Get")){
                strURL = "http://evaluation.esy.es/get.php";
                post_data = URLEncoder.encode("Professor","UTF-8")+"="+URLEncoder.encode(strings[1],"UTF-8");
                professor = strings[1];
            }
                URL url = new URL(strURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                        result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(context).create();
    }
    protected void onPostExecute(String result) {

        final Intent[] intent = new Intent[1];

        if (result != null) {

            final String[] strSplit = result.split("-");

            if (type.equals("Login")) {

                if (strSplit[0].equals("Sorry!")) {
                    alertDialog.setTitle("Login Unsuccessful");
                    alertDialog.setMessage("Wrong username or password.");
                    alertDialog.show();
                } else {

                    alertDialog.setTitle("Login Success");
                    alertDialog.setMessage(strSplit[1]);
                    alertDialog.show();

                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (strSplit[0].equals("Admin")) {
                                        intent[0] = new Intent(context.getApplicationContext(), Admin.class);
                                        context.startActivity(intent[0]);
                            } else if (strSplit[0].equals("Student")) {
                                        intent[0] = new Intent(context.getApplicationContext(), Student.class);
                                        intent[0].putExtra("username", username);
                                        twoName = strSplit[1].split("!");
                                        intent[0].putExtra("name", twoName[0]);
                                        context.startActivity(intent[0]);
                            }
                            timer.cancel();
                        }
                    }, 1500);

                }
            } else if (type.equals("Register")) {
                alertDialog.setTitle("Registration Status");
                alertDialog.setMessage(result);
                alertDialog.show();

                if (result.equals("Registration Successful!")) {
                    alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            intent[0] = new Intent(context.getApplicationContext(), Login.class);
                            context.startActivity(intent[0]);
                        }
                    });
                }

            }else if (type.equals("Save")){
                alertDialog.setTitle("Evaluation Status");
                alertDialog.setMessage(result);
                alertDialog.show();
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        intent[0] = new Intent(context.getApplicationContext(), Student.class);
                        intent[0].putExtra("name", name);
                        context.startActivity(intent[0]);
                    }
                });
            }else if(type.equals("Get")){
                        if (!result.contains("Error! No evaluation yet")) {
                            two = result.split(";");
                            score = two[0].split("-");
                            comment = two[1].split("/");
                            gcomment = comment[0].split("-");
                            bcomment = comment[1].split("-");
                        }

                        intent[0] = new Intent(context.getApplicationContext(), Evaluation.class);
                        intent[0].putExtra("professor", professor);
                        intent[0].putExtra("score", score);
                        intent[0].putExtra("gcomment", gcomment);
                        intent[0].putExtra("bcomment", bcomment);
                        context.startActivity(intent[0]);
            }

        } else {

            alertDialog.setTitle("Error");
            alertDialog.setMessage("Could not connect to the server!");
            alertDialog.show();
        }
    }


    protected void onProgressUpdate(Void... values ){
        super.onProgressUpdate(values);
    }


}
