package com.example.fragrancestore;

public class Utils {
    public static String getFilterString(String email) {
        String filter = "?$filter=(";
        filter += "email eq " + "'" + email + "'" + ")";

        return filter;
    }
}
