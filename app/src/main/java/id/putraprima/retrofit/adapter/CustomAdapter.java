package id.putraprima.retrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.RecipeActivity;
import id.putraprima.retrofit.api.models.DataRecipe;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

//    private Envelope<List<DataRecipe>> dataList;
//    private Context context;
    ArrayList<DataRecipe> dataList;
    private Onclick listener;

    public CustomAdapter(ArrayList<DataRecipe> dataList, RecipeActivity onclick) {
        this.dataList = dataList;
        this.listener = (Onclick) onclick;
    }


    public interface Onclick {
        void clickItem(DataRecipe model);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txtid, textNama, txtdesc, txtbahan, txtlangkah;
        private ImageView ivfoto;

        CustomViewHolder(View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.tv_id);
            textNama =itemView.findViewById(R.id.tv_name_resep);
            txtdesc =itemView.findViewById(R.id.tv_desc);
            txtbahan = itemView.findViewById(R.id.tv_bahan);
            txtlangkah = itemView.findViewById(R.id.tv_step);
            ivfoto = itemView.findViewById(R.id.img_avatar);


        }

        public void bind(final DataRecipe model, final Onclick onModelClick){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModelClick.clickItem(model);
                }
            });
        }
    }


    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipe, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {


        DataRecipe dataRecipe = dataList.get(position);
        holder.txtid.setText(Integer.toString(dataRecipe.getId()));
        holder.textNama.setText(dataRecipe.getNamaResep());
        holder.txtdesc.setText(dataRecipe.getDeskripsi());
        holder.txtbahan.setText(dataRecipe.getBahan());
        holder.txtlangkah.setText(dataRecipe.getLangkahPembuatan());
        holder.bind(dataRecipe, listener);

        Picasso.get().load("https://mobile.putraprima.id/uploads/"+dataRecipe.getFoto()).into(holder.ivfoto);
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }


}