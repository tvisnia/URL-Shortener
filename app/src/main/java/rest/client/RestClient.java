package rest.client;

import rest.api.ConnectionInterface;
import retrofit.RestAdapter;

/**
 * Created by tomek on 27.07.15.
 */
public class RestClient {
    public static final String BASE_URL = "http://ujeb.se";

    private RestAdapter restAdapter;
    private ConnectionInterface connectionInterface;

    public RestClient() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        connectionInterface = restAdapter.create(ConnectionInterface.class);
    }

    public ConnectionInterface getConnectionInterface() {
        return connectionInterface;
    }
}
