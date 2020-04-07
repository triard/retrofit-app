package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.ErorResponse;
import id.putraprima.retrofit.api.models.ErrorUtils;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtName, txtEmail, txtPass, txtConPass;
    private View rView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rView = findViewById(android.R.id.content).getRootView();
        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPass = findViewById(R.id.txt_pass);
        txtConPass = findViewById(R.id.txt_conpass);
    }

    private void register(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<RegisterResponse> call = service.doRegister(new RegisterRequest(txtName.getText().toString(), txtEmail.getText().toString(), txtPass.getText().toString(), txtConPass.getText().toString()));
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                try {
                    if(response.body()!=null){
                        setResponse(rView, "Daftar Berhasil");
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                    if(response.errorBody()!=null) {
                        ErorResponse error = ErrorUtils.parseError(response);
                        if (error.getError().getName() != null && error.getError().getEmail() != null && error.getError().getPassword() != null) {
                            Toast.makeText(RegisterActivity.this, "response message: " + error.getError().getName().get(0) +", "+error.getError().getEmail().get(0) + " and "+error.getError().getPassword().get(0), Toast.LENGTH_LONG).show();
                        }
                        else if (error.getError().getName()!=null) {
                            Toast.makeText(RegisterActivity.this, "response message: "+error.getError().getName().get(0), Toast.LENGTH_LONG).show();
                        } else if (error.getError().getEmail()!=null) {
                            Toast.makeText(RegisterActivity.this, "response message: "+error.getError().getEmail().get(0), Toast.LENGTH_LONG).show();
                        } else if (error.getError().getPassword()!=null) {
                            for(int i =0;i<error.getError().getPassword().size();i++){
                                Toast.makeText(RegisterActivity.this, "response message: "+error.getError().getPassword().get(i), Toast.LENGTH_LONG).show();
                            }
                        }else {

                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                setResponse(rView, "Daftar Gagal");
            }
        });
    }

    public void handleRegister(View view) {
        if (txtPass.getText().toString().equals(txtConPass.getText().toString())){
            register();
        }else{
            setResponse(rView, "Password tidak cocok");
        }
    }

    public void setResponse(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
