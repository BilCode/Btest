package learn.b.testb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import learn.b.testb.model.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Call<List<Repo>> repos = getwebService().listRepos("octocat");
        repos.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<Repo> contributors = (List<Repo>) response.body();
                for (Repo contributor : contributors) {
                    System.out.println(contributor.getName());
                    Toast.makeText(getApplicationContext(), " name: " + contributor.getName() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });

    }

    private WebServices getwebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServices service = retrofit.create(WebServices.class);
        return service;
    }
}
