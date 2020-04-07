package id.putraprima.retrofit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.ErorResponse;
import id.putraprima.retrofit.api.models.ErrorUtils;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.models.Session;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText emailText, passText;
    private View rView;
    private TextView appName, appVer;
    private Session session;
    public Session getSession() {
        return SplashActivity.session;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rView = findViewById(android.R.id.content).getRootView();
        emailText = findViewById(R.id.edtEmail);
        passText = findViewById(R.id.edtPassword);
        appName = findViewById(R.id.mainTxtAppName);
        appVer = findViewById(R.id.mainTxtAppVersion);

        session = getSession();
        appName.setText(session.getApp());
        appVer.setText(session.getVersion());
    }

    private void login(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<LoginResponse> call = service.doLogin(new LoginRequest(emailText.getText().toString(), passText.getText().toString()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try {
                    if(response.body()!=null){
                        new LoginResponse(response.body().token, response.body().token_type, response.body().expiresIn);
                        setResponse(rView, "Berhasil login");
                        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                        i.putExtra("token", response.body().token_type + " " + response.body().token);
                        startActivity(i);
                    }
                    if(response.errorBody()!=null){
                        ErorResponse error = ErrorUtils.parseError(response);
                        if(error.getError().getEmail()!=null && error.getError().getPassword()!=null){
                            Toast.makeText(MainActivity.this, "response message: "+error.getError().getPassword().get(0) + "and" + error.getError().getEmail().get(0), Toast.LENGTH_LONG).show();
                        }else if(error.getError().getEmail()!=null){
                            Toast.makeText(MainActivity.this, "response message: "+error.getError().getEmail().get(0), Toast.LENGTH_LONG).show();
                        }else if(error.getError().getPassword()!=null){
                            Toast.makeText(MainActivity.this, "response message: "+error.getError().getPassword().get(0), Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                setResponse(rView, "Login Gagal");
            }
        });
    }

    public void handleLogin(View view) {
        login();
    }

    public void setResponse(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void gotoRegist(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}
