package ftxApi;

import com.google.gson.Gson;
import ftxApi.model.CoinResponseModel;
import ftxApi.model.FutureResponseModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class FtxClient {
    private static final String BASE_URL = "https://ftx.com/";
    private final HttpClient client;

    public FtxClient() {
        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }

    public CoinResponseModel getCoins()
    {
        return get("api/coins", CoinResponseModel.class);
    }

    public FutureResponseModel getFutures()
    {
        return get("api/futures", FutureResponseModel.class);
    }

    private <T> T get(String endpoint, Class<T> obj) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        return new Gson().fromJson(response, obj);
    }
}
