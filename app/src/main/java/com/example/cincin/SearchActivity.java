package com.example.cincin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.SearchView;

import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity { //AppCompatActivity klase kai reikia veikloms nauju f-ju senuose android

    final static private String COCTAIL_API = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
    static private String query;
    private ArrayList<Coctail> coctailList = new ArrayList<Coctail>();
    private Adapter adapter; //tarpininkas tarp xml ir searchactivitysearch_no_results
    SearchView searchView = null;
    private RecyclerView recyclerView; //korteliu vaizdas

    @Override
    //The Override-Annotation is just a hint for the compiler that you want to overwrite a certain function. The compiler will then check parent-classes and interfaces if the function exists there. If not, you will get a compile-error
    protected void onCreate(Bundle savedInstanceState) {  //Bundle savedInstanceState - isaugoti veiklos busenai, kur naudojam vel atnaujinus, pradzioj null, pvz.: lango slinkimo pozicija
        super.onCreate(savedInstanceState); //tuscio lango sukurimas
        setContentView(R.layout.activity_search); //suteikti langui si vaizda
        // ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // adds item to action bar
        getMenuInflater().inflate(R.menu.search, menu); // Get Search item from action bar and Get Search service
        MenuItem searchItem = menu.findItem(R.id.action_search); //vartotojas irasys duomenis
        SearchManager searchManager = (SearchManager) SearchActivity.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(SearchActivity.this.getComponentName()));
            searchView.setIconified(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //meniu juostoj lupa
        return super.onOptionsItemSelected(item);
    } // Every time when you press search button on keypad an Activity is recreated which in turn calls this function

    @Override
    protected void onNewIntent(Intent intent) { // Get search query
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);  //vartotojas ives pvz Margarita
            if (searchView != null) {
                searchView.clearFocus(); //isvalo kursoriu
            }
            //is visu kokteiliu saraso sukuriamas sarasas pagal ieskoma kokteilio pavadinima
            AsyncFetch asyncFetch = new AsyncFetch(); //paleidziama nauja gija - nuskaitymui JSON is API
            asyncFetch.execute();  //vykdo kas toje klaseje numatyta. Butina execute
        }
    }

    private class AsyncFetch extends AsyncTask<String, String, ArrayList<Coctail>> { //lygiag uzd. apdorot //AsyncTask perduoda parametrus i doInBackground
        //https://medium.com/nybles/making-use-of-the-android-asynctask-class-30469180a1d2
        //parametrai eina i doInBackground // Params, Progress and Result (per result grazins rezultata)
        ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this);

        @Override
        protected void onPreExecute() { //metodas vykdomas pries doInBackground metoda. Paprasysime palaukti vartotojo, kol gausim duomenis
            super.onPreExecute(); //paleidzia gija pries doInBackground
            progressDialog.setMessage(getResources().getString(R.string.search_loading_data));
            progressDialog.setCancelable(false); //negales atsaukti ir tures islaukti
            progressDialog.show(); //rodymas vaizdas kol laukia/igalinti
        }

        @Override
        protected ArrayList<Coctail> doInBackground(String... params) { //jis bus vykdomas tuo metu kai vartotojas matys progress dialoga
            //skirtas duomenu paemimui is API
            try {
                JSONObject jsonObject = JSON.readJsonFromUrl(COCTAIL_API + query); //perduodamas adresas
                JSONArray jsonArray = null;
                coctailList = new ArrayList<Coctail>();
                try {
                    if (jsonObject.isNull("drinks")) {
                    } else {
                        jsonArray = JSON.getJSONArray(jsonObject);
                        coctailList = JSON.getList(jsonArray);
                    }
                } catch (JSONException e) {
                    
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(SearchActivity.this, getResources().getText(R.string.search_error_reading_data) + e.getMessage(), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });


                    //  Toast.makeText(SearchActivity.this, getResources().getText(R.string.search_error_reading_data) + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                return coctailList;
            } catch (IOException e) {  //isimtys, apdoros duomenisir isvesvartotojui

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SearchActivity.this, getResources().getText(R.string.search_error_reading_data) + e.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                //  Toast.makeText(SearchActivity.this, getResources().getText(R.string.search_error_reading_data) + e.getMessage(), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SearchActivity.this, getResources().getText(R.string.search_error_reading_data) + e.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                //  Toast.makeText(SearchActivity.this, getResources().getText(R.string.search_error_reading_data) + e.getMessage(), Toast.LENGTH_LONG).show();

            } //baigiasi JSONE e
            return null;  //jei bus problemu grazinsim null
        }

        protected void onPostExecute(ArrayList<Coctail> coctailList) { //po
            progressDialog.dismiss(); //duomenys gauti, naikinam laukimo vaizda

            if (coctailList == null || coctailList.size() == 0) {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SearchActivity.this, getResources().getString(R.string.search_no_results), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }

            //duomenu perdavimas Adapteriui ir RecyclerView sukurimas
            recyclerView = (RecyclerView) findViewById(R.id.coctail_list);
            adapter = new Adapter(SearchActivity.this, coctailList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        }// baigiasi onPostExecute
    }// baigiasi AsyncFetch
}// baigiasi SearchActivity

