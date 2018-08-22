package uebung23_1;

import java.lang.invoke.MethodHandles;

public class Main {

    private static final String PINS_FILE_NAME = "pins.csv";
    private static final String PASSWORD_ALPHABET = "123456789abcdefghijklmnopqrstuvwxyz";


    public static void main(String[] args) {
        String filename = MethodHandles.lookup().lookupClass().getResource(PINS_FILE_NAME).getPath();
        PasswordGuessing pg = new PasswordGuessing(filename, PASSWORD_ALPHABET);
        pg.guessPasswords();
    }
}
