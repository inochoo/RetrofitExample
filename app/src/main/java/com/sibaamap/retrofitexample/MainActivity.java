package com.sibaamap.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sibaamap.retrofitexample.api.ApiService;
import com.sibaamap.retrofitexample.model.Currency;
import com.sibaamap.retrofitexample.model.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTerms;
    private TextView tvSource;
    private TextView tvUsdVnd;
    private Button btnCallApi;
    private TextView tvPostResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSource = findViewById(R.id.tv_source);
        tvTerms = findViewById(R.id.tv_terms);
        tvUsdVnd = findViewById(R.id.tv_usd_vnd);
        btnCallApi = findViewById(R.id.btn_call_api);
        tvPostResult = findViewById(R.id.tv_post_result);

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickCallApi();
                sendPost();
            }
        });

    }
    //Link API : http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    //https://jsonplaceholder.typicode.com/posts
    private void clickCallApi() {

        Map<String, String> options = new HashMap<>();
        options.put("access_key","843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies","VND");
        options.put("source","USD");
        options.put("format","1");
        ApiService.apiService.convertUsdToVnd2(options).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();

                Currency currency = response.body();
                if(currency != null && currency.isSuccess()){
                    tvTerms.setText(currency.getTerms());
                    tvSource.setText(currency.getSource());
                    tvUsdVnd.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                }

            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPost(){
        Post post = new Post(10, 101,"Sibaamap","fkb channel");

        ApiService.apiService.sendPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();

                Post postResult = response.body();
                if (postResult != null){
                    tvPostResult.setText(postResult.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}