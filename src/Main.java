

public class Main {

    private static final String PINS_FILE_NAME = "src/pins.csv";
    private static final String PASSWORD_ALPHABET = "123456789abcdefghijklmnopqrstuvwxyz";


    public static void main(String[] args) {
        PasswordGuessing pg = new PasswordGuessing(PINS_FILE_NAME, PASSWORD_ALPHABET);
        pg.guessPasswords();
    }
}
