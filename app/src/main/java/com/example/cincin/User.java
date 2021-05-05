package com.example.cincin;

        import android.content.Context;
        import android.content.SharedPreferences;

public class User {
// 1. prasyti klases kintamuosius (argumentus, pozymius)

    private String username;
    private String password;
    private String email;
    private String birthDate;

    private SharedPreferences sharedPreferences;

    private static final String PREFERENCES_PACKAGE_NAME ="com.example.cincin";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBERME_KEY = "rememberMe";

    //2. Konstruktoriaus kurimas. Siuo atveju bevardis. Galima ir nekurti - sukuriamas automatiskai
    public User (){
    }

    //Konstr. skirtas registracijos langui, perduosime tris parametrus
    public User (String username, String password, String email, String date){
        this.username=username;
        this.password=password;
        this.email=email;
        this.birthDate=birthDate;
    }

    //konstrukorius prisijungimo langui, perduosime du parametrus
    public User (String username, String password){
        this.username=username;
        this.password=password;
//3. Geteriai ir seteriai
    }
    //konstruktorius (panaudotas prisijungimo lange LoginActivity)
    public User (Context context){
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_PACKAGE_NAME, Context.MODE_PRIVATE);
    }


    public String getUserNameForLogin(){
        return this.sharedPreferences.getString(USERNAME_KEY, "");
    } //get atitinka grazinancia f-ja be parametru

    public void setUserNameForLogin(String username){
        this.sharedPreferences.edit().putString(USERNAME_KEY, username).commit();
    }

    public String getUserPasswordForLogin(){
        return this.sharedPreferences.getString(PASSWORD_KEY, "");
    }

    public void setUserPasswordForLogin(String password){//seteris atititnka negrazinancia funkcija su parametrais
        this.sharedPreferences.edit().putString(PASSWORD_KEY, password).commit();
    }

    public boolean isRememberedForLogin(){
        return this.sharedPreferences.getBoolean(REMEMBERME_KEY, false);
    }

    public void setRemembermeKeyForLogin(boolean remembermeKey){
        this.sharedPreferences.edit().putBoolean(REMEMBERME_KEY, remembermeKey).commit();
    }

    public String getUserNameForRegistration(){
        return username;
    }

    public void setUserNameForRegistration(String username){
        this.password=username;
    }

    public String getUserPasswordForRegistration(){
        return password;
    }

    public void setUserPasswordForRegistration(String password){
        this.password=password;
    }

    public String getUserEmailForRegistration(){
        return email;
    }

    public void setUserEmailForRegistration(String email){
        this.email=email;
    }

    public String getBirthDate() {return birthDate;}

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
}
