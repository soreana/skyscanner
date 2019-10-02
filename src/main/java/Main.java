import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.skyscanner.net/g/conductor/v1/fps3/search/e8547de7-8513-4764-801c-0518edf8ffee?geo_schema=skyscanner&carrier_schema=skyscanner&response_include=query%3Bdeeplink%3Bsegment%3Bstats%3Bfqs%3Bpqs&_=1568846015017")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "utf-8")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.2 Safari/605.1.15")
                .addHeader("Referer", "https://www.skyscanner.net/transport/flights/ams/ika/190923/191015/?adults=1&children=0&adultsv2=1&childrenv2=&infants=0&cabinclass=economy&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false&ref=home")
                .addHeader("X-Skyscanner-DeviceDetection-IsTablet", "false")
                .addHeader("Skyscanner-UTID", "d0154516-b2d9-4dbb-9249-75cfee7dc0ec")
                .addHeader("X-Distil-Ajax", "azezcavtdrrxfqrtbw")
                .addHeader("X-Skyscanner-ChannelId", "website")
                .addHeader("X-Skyscanner-DeviceDetection-IsMobile", "false")
                .addHeader("X-Skyscanner-Traveller-Context", "d0154516-b2d9-4dbb-9249-75cfee7dc0ec")
                .addHeader("X-Skyscanner-ViewId", "cd72f8f3-ab09-4d2c-aa4c-b7334a2448bf")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("X-Gateway-ServedBy", "gw53.skyscanner.net")
                .addHeader("X-Skyscanner-If-None-Match", "96bf52fe-f9d1-4205-bad8-14a14dbbd684")
                .addHeader("X-Skyscanner-MixPanelId", "[object Response]")
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        String resBody = response.body().string();
        System.out.println(resBody);

        JSONObject jBody = new JSONObject(resBody);
        JSONArray itineraries = jBody.getJSONArray("itineraries");
        System.out.println(itineraries);
        int index = 0;
        for (int i = 0; i < itineraries.length(); i++) {
            JSONObject object = itineraries.getJSONObject(i);
            System.out.println(object);
            JSONArray pricingOptions = object.getJSONArray("pricing_options");
            for (int j = 0; j < pricingOptions.length(); j++) {
                JSONObject price = pricingOptions.getJSONObject(j).getJSONObject("price");
                if (price.has("amount"))
                    System.out.println( index + " price: " + price.getFloat("amount"));
                index++;
            }
        }

    }
}
