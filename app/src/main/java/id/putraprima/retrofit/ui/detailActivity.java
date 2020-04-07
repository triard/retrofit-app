package id.putraprima.retrofit.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import id.putraprima.retrofit.R;

public class detailActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "imageUrl";
    TextView txtid, textNama, txtdesc, txtbahan, txtlangkah;
    private ImageView ivfoto;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String image_detail = getIntent ().getStringExtra (EXTRA_URL);
        txtid = findViewById(R.id.tv_id);
        textNama =findViewById(R.id.tv_name_resep);
        txtdesc =findViewById(R.id.tv_desc);
        txtbahan = findViewById(R.id.tv_bahan);
        txtlangkah = findViewById(R.id.tv_step);
        ivfoto = findViewById(R.id.img_avatar);

        txtid.setText(getIntent().getStringExtra("id"));
        textNama.setText(getIntent().getStringExtra("nama"));
        txtdesc.setText(getIntent().getStringExtra("desc"));
        txtbahan.setText(getIntent().getStringExtra("bahan"));
        txtlangkah.setText(getIntent().getStringExtra("langkah"));
        String image = getIntent().getStringExtra(EXTRA_URL);
        Picasso.get().load("https://mobile.putraprima.id/uploads/"+ image).into(ivfoto);


    }
}
