package id.putraprima.retrofit.api.models;

import com.google.gson.annotations.SerializedName;

public class DataRecipe {

	@SerializedName("bahan")
	private String bahan;

	@SerializedName("foto")
	private String foto;

	@SerializedName("langkah_pembuatan")
	private String langkahPembuatan;

	@SerializedName("nama_resep")
	private String namaResep;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

    public DataRecipe(int id,String namaResep,String deskripsi,String bahan, String langkahPembuatan, String foto   ) {
        this.bahan = bahan;
        this.foto = foto;
        this.langkahPembuatan = langkahPembuatan;
        this.namaResep = namaResep;
        this.id = id;
        this.deskripsi = deskripsi;
    }

    public void setBahan(String bahan){
		this.bahan = bahan;
	}

	public String getBahan(){
		return bahan;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setLangkahPembuatan(String langkahPembuatan){
		this.langkahPembuatan = langkahPembuatan;
	}

	public String getLangkahPembuatan(){
		return langkahPembuatan;
	}

	public void setNamaResep(String namaResep){
		this.namaResep = namaResep;
	}

	public String getNamaResep(){
		return namaResep;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}



	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"bahan = '" + bahan + '\'' + 
			",foto = '" + foto + '\'' + 
			",langkah_pembuatan = '" + langkahPembuatan + '\'' + 
			",nama_resep = '" + namaResep + '\'' + 
			",id = '" + id + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			"}";
		}
}