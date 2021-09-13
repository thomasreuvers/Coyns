import ftxApi.FtxClient;

public class Main {
    public static void main(String[] args) {
        var x = new FtxClient();
        var response = x.getCoins();

        response.result.forEach(coin -> System.out.println(coin.name));
    }
}
