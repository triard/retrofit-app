package id.putraprima.retrofit.api.models;

import com.google.gson.annotations.SerializedName;

public class ErorPasswordResponse {

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("error")
	private ErrorPassword error;

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setError(ErrorPassword error){
		this.error = error;
	}


	public ErrorPassword getError(){
			return error;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"status_code = '" + statusCode + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}