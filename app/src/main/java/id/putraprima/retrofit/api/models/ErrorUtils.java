package id.putraprima.retrofit.api.models;

import java.io.IOException;
import java.lang.annotation.Annotation;

import id.putraprima.retrofit.api.helper.ServiceGenerator;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static ErorResponse parseError(Response<?> response) {
        Converter<ResponseBody, ErorResponse> converter =
                ServiceGenerator.retrofit
                        .responseBodyConverter(ErorResponse.class, new Annotation[0]);

        ErorResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErorResponse();
        }

        return error;
    }
}
