package bookingdotcom;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HackerRankQuestions {

    public static int maxDifference(List<Integer> px) {
        int result = Integer.MIN_VALUE;
        int currMax = Integer.MIN_VALUE;
        for (int i = px.size() - 1; i >= 0; i--) {
            if (px.get(i) > currMax) {
                currMax = px.get(i);
            } else {
                int max = currMax - px.get(i);
                result = Math.max(max, result);
            }
        }
        return result == Integer.MIN_VALUE ? -1 : result;
    }

    public static int getCountries(String s, int p) throws Exception {
        int count = 0;
        JSONParser parser = new JSONParser();
        int currPage = 1;
        String url = "https://jsonmock.hackerrank.com/api/countries/search?name=%s&page=%s";
        Long total_pages = null;
        do {
            System.out.println("Page no = " + currPage);
            String jsonString = getUrlResponse(String.format(url, s, currPage));
            JSONObject response = (JSONObject) parser.parse(jsonString);
            JSONArray resArr = (JSONArray) response.get("data");
            Iterator iterator = resArr.iterator();
            if (total_pages == null) {
                total_pages = (long) response.get("total_pages");
                System.out.println("total_pages = " + total_pages);
            }
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                String name = (String) jsonObject.get("name");
                System.out.println(name + " ==> " + name.indexOf(s));
                long population = (long) jsonObject.get("population");
                if (population > p /*&& name.indexOf(s) > 0*/) {
                    count++;
                }
            }
            currPage++;
        } while (currPage <= total_pages);

        return count;


    }


    private static String getUrlResponse(String url) {
        StringBuilder output = new StringBuilder();
        try {
            URL urlObj = new URL(url);

            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
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

    public static void main(String[] args) throws Exception {
        //System.out.println(HackerRankQuestions.getCountries("co", 500));
        System.out.println(HackerRankQuestions.maxDifference(Arrays.asList(24, 24, 10, 10, 8, 8, 1)));
    }
}
