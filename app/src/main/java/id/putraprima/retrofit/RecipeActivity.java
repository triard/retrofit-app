package id.putraprima.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import id.putraprima.retrofit.adapter.CustomAdapter;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.DataRecipe;
import id.putraprima.retrofit.api.models.Envelope;
import id.putraprima.retrofit.api.services.ApiInterface;
import id.putraprima.retrofit.ui.detailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity implements CustomAdapter.Onclick {

    public static final String EXTRA_URL = "imageUrl";
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    int page=2;
    TextView pageview;
    ArrayList<DataRecipe> recipe;
    private ConstraintLayout mRecipeLayout;
    Button reloadbtn, loadmore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        recipe = new ArrayList<>();
        progressDoalog = new ProgressDialog(RecipeActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        recyclerView = findViewById(R.id.customRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter(recipe, this);
        recyclerView.setAdapter(adapter);
        mRecipeLayout = findViewById(R.id.recipeLayout);
        doLoad();

        loadmore = findViewById(R.id.loadmore);

        /*menambah warna pada SwipeRefreshLayout*/
        final SwipeRefreshLayout dorefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        dorefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        /*event ketika widget dijalankan*/
        dorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItem();
            }

            void refreshItem() {
                page=2;
                recipe.clear();
                doLoad();
                onItemLoad();
            }

            void onItemLoad() {
                dorefresh.setRefreshing(false);
            }

        });

    }


    @Override
    public void clickItem(DataRecipe model) {
        Intent intent = new Intent(RecipeActivity.this, detailActivity.class);
        intent.putExtra("id", model.getId());
        intent.putExtra("nama", model.getNamaResep());
        intent.putExtra("desc", model.getDeskripsi());
        intent.putExtra("bahan", model.getBahan());
        intent.putExtra("langkah", model.getLangkahPembuatan());
        intent.putExtra(EXTRA_URL, model.getFoto());
        startActivity(intent);
    }

    public void doLoad() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Envelope<List<DataRecipe>>> call = service.doRecipe();
        call.enqueue(new Callback<Envelope<List<DataRecipe>>>() {
            @Override
            public void onResponse(Call<Envelope<List<DataRecipe>>> call, Response<Envelope<List<DataRecipe>>> response) {
                if(response.body()!=null){
                    progressDoalog.dismiss();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        int id = response.body().getData().get(i).getId();
                        String namaResep = response.body().getData().get(i).getNamaResep();
                        String deskripsi = response.body().getData().get(i).getDeskripsi();
                        String bahan = response.body().getData().get(i).getBahan();
                        String langkahPembuatan = response.body().getData().get(i).getLangkahPembuatan();
                        String foto = response.body().getData().get(i).getFoto();
                        recipe.add(new DataRecipe(id, namaResep, deskripsi, bahan, langkahPembuatan, foto));
                        adapter.notifyDataSetChanged();
                    }
                    String message ="Load data sukses, harap tunggu.";
                    Snackbar snackbar = Snackbar.make(mRecipeLayout, message, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else if(response.errorBody()!=null) {
                    progressDoalog.dismiss();
                    String message = "Load data gagal, cek koneksi kamu";
                    Snackbar snackbar = Snackbar.make(mRecipeLayout, message, Snackbar.LENGTH_SHORT);
                    snackbar.show();

                }
            }

            @Override
            public void onFailure(Call<Envelope<List<DataRecipe>>> call, Throwable t) {
                progressDoalog.dismiss();
                String message = "Cek konesi kamu mungkin sedang bermasalah";
                Snackbar snackbar = Snackbar.make(mRecipeLayout, message, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

        });

    }

    public void DataPage2(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Envelope<List<DataRecipe>>> call = service.doLoadMore(page++)  ;
        call.enqueue(new Callback<Envelope<List<DataRecipe>>>() {
            @Override
            public void onResponse(Call<Envelope<List<DataRecipe>>> call, Response<Envelope<List<DataRecipe>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getData().size()!=0){
                        loadmore.setEnabled(false);
                        progressDoalog.dismiss();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            int id = response.body().getData().get(i).getId();
                            String namaResep = response.body().getData().get(i).getNamaResep();
                            String deskripsi = response.body().getData().get(i).getDeskripsi();
                            String bahan = response.body().getData().get(i).getBahan();
                            String langkahPembuatan = response.body().getData().get(i).getLangkahPembuatan();
                            String foto = response.body().getData().get(i).getFoto();
                            recipe.add(new DataRecipe(id, namaResep, deskripsi, bahan, langkahPembuatan, foto));
                            adapter.notifyDataSetChanged();
                        }

                        String message = "berhasil load data page " + response.body().getMeta().current_page;
                        Snackbar snackbar = Snackbar.make(mRecipeLayout, message, Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        loadmore.setEnabled(true);
                    }else{
                        progressDoalog.dismiss();
                        Snackbar snackbar = Snackbar.make(mRecipeLayout, "Isi Page kosong", Snackbar.LENGTH_SHORT);
                        snackbar.show();

                    }
                }else if(response.errorBody()!=null) {
                    progressDoalog.dismiss();
                    Snackbar snackbar = Snackbar.make(mRecipeLayout, "load data gagal pada page " +page, Snackbar.LENGTH_SHORT);
                    snackbar.show();

                }

            }

            @Override
            public void onFailure(Call<Envelope<List<DataRecipe>>> call, Throwable t) {
                progressDoalog.dismiss();
                Snackbar snackbar = Snackbar.make(mRecipeLayout, "gagal koneksi, coba cek koneksi internet anda ya:)", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

        });
    }

    public void HandleLoadMore(View view) {
        progressDoalog = new ProgressDialog(RecipeActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        DataPage2();
    }

}
