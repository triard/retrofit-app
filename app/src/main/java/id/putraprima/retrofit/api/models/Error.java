package id.putraprima.retrofit.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

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