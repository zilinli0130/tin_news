//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of RetrofitClient class.
// * Node: Class to create Retrofit service client builder
//**********************************************************************************************************************
package com.tzllzt.tinnews.network;
//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Network includes
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// System includes
import java.io.IOException;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class RetrofitClient {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    // Retrofit-> endpoint -> okHttp (do request) -> JSON -> GSON parse -> Java object
    // Static method for retrieving Retrofit builder
    public static Retrofit newInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    // Build the url header with API key
    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build();
            return chain.proceed(request);
        }

    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    private static final String API_KEY = "dc06108a30b04205b7a6ebffdf526fcf";
    private static final String BASE_URL = "https://newsapi.org/v2/";
}

