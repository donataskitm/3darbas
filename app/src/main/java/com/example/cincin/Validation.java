package com.example.cincin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String NAME_PATTERN = "^[a-zA-Z]{3,20}$"; //usernameRegexPatern
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9.!@_]{5,20}$";
    //private static final String EMAIL_PATTERN = "^[a-zA-Z0-9@._-]{10,50}$";
    //"^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isCredentialsValid(String credentials) {  //boolean - loginis kintamasis true (1) arba false (0)
        Pattern pattern = Pattern.compile(NAME_PATTERN); //sukuriamos username validacijos taisykles pagal nurodyta sablona
        Matcher matcher = pattern.matcher(credentials);//pagal anksciau sukurtas taisykles palyginami ivesti duomenys
        // boolean isUserNameValid; //grazins true arba false
        return matcher.find(); //matcher.matches(), grazins true arba false
    }

    public static boolean isPasswordValid(String email) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(email);
        // boolean isUserPasswordValid;
        // isUserPasswordValid = matcher.find(); //grazins true arba false
        return matcher.find();//isUserPasswordValid;
        //return matcher.matches();
    }

    public static boolean isEmailValid(String credentials) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(credentials);
        return matcher.find();
    }

    public static boolean isDateValid(String date) {

        Date date1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date1 == null) return false;
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date1);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month, day);

        //  long age = date2.getTime() - date1.getTime();
        //  age = age / (1000 * 60 * 60 * 24);
        //  if (age < 7665) { return false;}

        int years = (today.get(Calendar.YEAR)) - dob.get(Calendar.YEAR);
        boolean monthsG = dob.get(Calendar.MONTH) > today.get(Calendar.MONTH);
        boolean monthsE = dob.get(Calendar.MONTH) == today.get(Calendar.MONTH);
        boolean daysG = dob.get(Calendar.DAY_OF_MONTH) >= today.get(Calendar.DAY_OF_MONTH);

        if (years >= 21) {
            return true;
        } else if (years == 20) {
            if (monthsG || monthsE && daysG) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }//isDateValid
}
