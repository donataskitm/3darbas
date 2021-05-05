package com.example.cincin;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.Reader;
        import java.net.URL;
        import java.nio.charset.Charset;
        import java.util.ArrayList;
        import java.util.regex.Pattern;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

public class JSON {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException { //throws dirbtinai sukelia situacija. Iesko catch, jeineranda apdoroja bevarde apdorokle

        InputStream is = new URL(url).openStream();
        try { //
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json; //blokas baigiamas reiksmes grazinimu
        } finally { /// vykdom ir ivykus klaidai
            is.close(); //uzdaro inputstream ir atlaisvina resursus
        }
    }
    //paims json obj ir grazins json masyva
    public static ArrayList<Coctail> getList(JSONArray jsonArray) throws JSONException{ //konvertuosim JSON i JAVA sarasa
        ArrayList<Coctail> coctailList = new ArrayList<Coctail>(); //kokios klases objektus talpinsim// gali buti sarase tik vieno tipo elementai
        //isimti duomenis(data) ir issaugoti Coctail objektu sarase (coctailList)
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Coctail coctail = new Coctail( //      //kokia seka perduosime duomenis
                    jsonObject.getString("strDrink"),
                    jsonObject.getString("strCategory"),
                    jsonObject.getString("strGlass"),
                    jsonObject.getString("strInstructions"),
                    jsonObject.getString("strDrinkThumb")
            );
            coctailList.add(coctail);
        }
        return coctailList;
    }

    public static JSONArray getJSONArray(JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray = jsonObject.getJSONArray("drinks");//trk
        return jsonArray;
    }
      //if (coctail.getName().toLowerCase().contains(name.toLowerCase())){//    contains iesko zodzio dalies
}