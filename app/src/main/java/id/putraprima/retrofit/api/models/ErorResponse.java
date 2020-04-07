package id.putraprima.retrofit.api.models;

import com.google.gson.annotations.SerializedName;

public class ErorResponse{

	@SerializedName("status_code")
	private int statusCode;
	@SerializedName("error")
	private Error error;

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setError(Error error){
		this.error = error;
	}

	public Error getError(){
		return error;
//		if(error.getEmail()!=null){
//			return String.valueOf(error.getEmail().get(0));
//		}else if(error.getName()!=null){
//			return String.valueOf(error.getName().get(0));
//		}else {
//			return String.valueOf(error.getPassword().get(0));
//		}
	}

	@Override
 	public String toString(){
		return 
			"ErorResponse{" + 
			"status_code = '" + statusCode + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}