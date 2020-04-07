package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.Data;
import id.putraprima.retrofit.api.models.ErorPasswordResponse;
import id.putraprima.retrofit.api.models.ErorResponse;
import id.putraprima.retrofit.api.models.ErrorPasswordUtils;
import id.putraprima.retrofit.api.models.ErrorUtils;
import id.putraprima.retrofit.api.models.PasswordRequest;
import id.putraprima.retrofit.api.models.ProfileRequest;
import id.putraprima.retrofit.api.models.ProfileResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePasswordActivity extends AppCompatActivity {
    private EditText newPass, newConPass;
    String token, newPassVal, newConPassVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        newPass = findViewById(R.id.txt_new_pass);
        newConPass = findViewById(R.id.txt_new_con_pass);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            token = bundle.getString("token");
        }
    }

    boolean updatePassword() {
        newPassVal = newPass.getText().toString();
        newConPassVal = newConPass.getText().toString();
    //    }else{
            ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
            Call<Data<ProfileResponse>> call = service.updatePassword(token, new PasswordRequest(newPassVal, newConPassVal));
            call.enqueue(new Callback<Data<ProfileResponse>>() {
                @Override
                public void onResponse(Call<Data<ProfileResponse>> call, Response<Data<ProfileResponse>> response) {
                    try {
                        if(response.body()!=null){
                            if (!newConPassVal.equals(newPassVal)) {
                                Toast.makeText(UpdatePasswordActivity.this, "Password harus sama", Toast.LENGTH_SHORT).show();
                             //   return false;
                            } else if (newPassVal.trim().length() < 8) {
                                Toast.makeText(UpdatePasswordActivity.this, "Minimal 8 karakter", Toast.LENGTH_SHORT).show();
                               // return false;
                            }else {
                                Toast.makeText(UpdatePasswordActivity.this, "Berhasil Update Password ", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(UpdatePasswordActivity.this, ProfileActivity.class);
                                i.putExtra("token", token);
                                startActivity(i);
                            }
                        }
                        if(response.errorBody()!=null){
                            ErorPasswordResponse error = ErrorPasswordUtils.parseError(response);
                            for(int i =0;i<error.getError().getPassword().size();i++) {
                                Toast.makeText(UpdatePasswordActivity.this, "response message: " + error.getError().getPassword().get(i), Toast.LENGTH_LONG).show();
                            }
                            }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<Data<ProfileResponse>> call, Throwable t) {
                    Toast.makeText(UpdatePasswordActivity.this, "Gagal Update Password", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
     //   }

    }

    public void handleUpdatePassword(View view) {
        if (updatePassword()){

        }
    }
}
