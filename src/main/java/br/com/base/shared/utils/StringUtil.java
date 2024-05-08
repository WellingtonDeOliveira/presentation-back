package br.com.base.shared.utils;

public class StringUtil {

    public static String like(String query) {
        if (query == null)
            return null;

        return "%" + query.trim().toLowerCase() + "%";
    }
}
