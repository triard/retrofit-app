package id.putraprima.retrofit.api.models;

import java.io.IOException;
import java.lang.annotation.Annotation;

import id.putraprima.retrofit.api.helper.ServiceGenerator;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorPasswordUtils {

    public static ErorPasswordResponse parseError(Response<?> response) {
        Converter<ResponseBody, ErorPasswordResponse> converter =
                ServiceGenerator.retrofit
                        .responseBodyConverter(ErorPasswordResponse.class, new Annotation[0]);

        ErorPasswordResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErorPasswordResponse();
        }

        return error;
    }
}
