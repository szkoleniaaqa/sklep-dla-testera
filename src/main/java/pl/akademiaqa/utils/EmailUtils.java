package pl.akademiaqa.utils;

import com.github.javafaker.Faker;

public class EmailUtils {

    // prywatny konstruktor

    public static String getRandomEmail() {
        return new Faker().internet().emailAddress();
    }
}
