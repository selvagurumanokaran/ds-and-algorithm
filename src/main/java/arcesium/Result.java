package arcesium;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.joining;

import java.net.URL;

class Holding {
    String date;
    String security;
    int quantity;
    String portfolio;
}

class Holdings {
    int totalRecords;
    int recordsPerPage;
    int page;
    String nextPage;
    List<Holding> data;
}

class Price {
    String date;
    String security;
    double price;
}

class Prices {
    int totalRecords;
    int recordsPerPage;
    int page;
    String nextPage;
    List<Price> data;
}

public class Result {

    public static double calculateNAV(String date) {
        ConcurrentMap<String, Integer> holdingsMap = new ConcurrentHashMap<>();
        BlockingQueue<Price> queue = new LinkedBlockingDeque<>();
        final int[] totalRecords = new int[1];
        final int[] nav = new int[1];
//        ConcurrentMap<String, Double> marketPriceMap = new ConcurrentHashMap<>();
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable holdingsProducer = () -> {
            String url = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/paging/holding_start";
            while (url != null) {
                String holdingsObj = getUrlResponse(url);
                Holdings holdings = new Gson().fromJson(holdingsObj, Holdings.class);
                if (totalRecords[0] == -1) {
                    synchronized (totalRecords) {
                        if (totalRecords[0] == -1)
                            totalRecords[0] = holdings.totalRecords;
                    }
                }
                holdings.data.stream()
                        .filter(holding -> holding.date.equals(date))
                        .forEach(holding -> holdingsMap.merge(holding.security, holding.quantity, Integer::sum));
                url = holdings.nextPage;
            }
            latch.countDown();
        };

        Runnable pricingProducer = () -> {
            String url = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/paging/pricing_start";
            while (url != null) {
                String holdingsObj = getUrlResponse(url);
                Prices prices = new Gson().fromJson(holdingsObj, Prices.class);
                for (Price price : prices.data) {
                    if (price.date.equals(date)) {
                        try {
                            queue.put(price);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                url = prices.nextPage;
            }
            latch.countDown();
        };

        Runnable consumer = () -> {

            int records = 0;
            while (records != totalRecords[0]) {
                Price price = null;
                try {
                    price = queue.take();
                    if (!holdingsMap.containsKey(price.security)) {
                        queue.put(price);
                        continue;
                    }
                    synchronized (nav) {
                        nav[0] += price.price * holdingsMap.compute(price.security, (security, integer) -> null);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                records++;
            }

            latch.countDown();
        };
        executorService.execute(holdingsProducer);
        executorService.execute(pricingProducer);
        executorService.execute(consumer);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return nav[0];
    }


    private static String getUrlResponse(String url) {
        StringBuilder output = new StringBuilder();
        try {
            URL oracle = new URL(url);

            HttpURLConnection urlConnection = (HttpURLConnection) oracle.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    output.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        double v = Result.calculateNAV("20190101");
        System.out.println(v);
    }
}
