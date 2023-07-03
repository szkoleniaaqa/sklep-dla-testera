package pl.akademiaqa.utils;

import java.util.ResourceBundle;

public class Properties {

    // prywatny konstruktor

    public static String getProperty(String propertyName) {
        return ResourceBundle.getBundle("application").getString(propertyName);
    }
}
