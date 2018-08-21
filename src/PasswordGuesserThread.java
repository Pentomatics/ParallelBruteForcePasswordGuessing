
/*
public class PasswordGuesserThread extends Thread {

    public static final String PINS_FILE_NAME = "src/pins.csv";
    public static final String PASSWORD_ALPHABET = "123456789abcdefghijklmnopqrstuvwxyz";

    String name;
    String encryptedPW;

    public PasswordGuesserThread(String name, String encryptedPW) {
        this.name = name;
        this.encryptedPW = encryptedPW;
    }

    public void run() {
        PasswordGuessing pg = new PasswordGuessing(PINS_FILE_NAME, PASSWORD_ALPHABET);
        pg.guessPassword(name, encryptedPW);
    }
}*/
