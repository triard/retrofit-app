package id.putraprima.retrofit.api.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ErrorPassword {

	@SerializedName("password")
	private List<String> password;

	public void setPassword(List<String> password){
		this.password = password;
	}

	public List<String> getPassword(){
		return password;
	}

	@Override
 	public String toString(){
		return 
			"Error{" + 
			"password = '" + password + '\'' + 
			"}";
		}
}