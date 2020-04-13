package id.putraprima.retrofit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Error{

	@SerializedName("password")
	private List<String> password;

	@SerializedName("name")
	private List<String> name;

	@SerializedName("email")
	private List<String> email;

	public void setPassword(List<String> password){
		this.password = password;
	}

	public List<String> getPassword(){
		return password;
	}

	public void setName(List<String> name){
		this.name = name;
	}

	public List<String> getName(){
		return name;
	}

	public void setEmail(List<String> email){
		this.email = email;
	}

	public List<String> getEmail(){
		return email;
	}

	@SerializedName("nama_resep")
	@Expose
	private List<String> nama_resep = null;
	public List<String> getNama_resep() {
		return nama_resep;
	}
	public void setNama_resep(List<String> nama_resep) {
		this.nama_resep = nama_resep;
	}

	@SerializedName("deskripsi")
	@Expose
	private List<String> deskripsi = null;
	public List<String> getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(List<String> deskripsi) {
		this.deskripsi = deskripsi;
	}

	@SerializedName("bahan")
	@Expose
	private List<String> bahan = null;
	public List<String> getBahan() {
		return bahan;
	}
	public void setBahan(List<String> bahan) {
		this.bahan = bahan;
	}

	@SerializedName("langkah_pembuatan")
	@Expose
	private List<String> langkah_pembuatan = null;
	public List<String> getLangkah_pembuatan() {
		return langkah_pembuatan;
	}
	public void setLangkah_pembuatan(List<String> langkah_pembuatan) {
		this.langkah_pembuatan = langkah_pembuatan;
	}

	@SerializedName("foto")
	@Expose
	private List<String> foto = null;
	public List<String> getFoto() {
		return foto;
	}
	public void setFoto(List<String> foto) {
		this.foto = foto;
	}

	@Override
 	public String toString(){
		return 
			"Error{" + 
			"password = '" + password + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}