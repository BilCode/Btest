package learn.b.testb;

import java.util.List;

import learn.b.testb.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bilal on 7/28/17.
 */

public interface WebServices {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
