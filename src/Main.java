

public class Main {

    public static final String PINS_FILE_NAME = "src/pins.csv";
    public static final String PASSWORD_ALPHABET = "123456789abcdefghijklmnopqrstuvwxyz";


    public static void main(String[] args) {
        PasswordGuessing pg = new PasswordGuessing(PINS_FILE_NAME, PASSWORD_ALPHABET);
        pg.guessPasswords();
    }

    // Todo delte comment
    // Todo delete PinGenerator
    // Todo unit tests???



    /*
    public void encryptPasswords() {
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            String pwd = passwords[i];
            try {
                System.out.println(name + " - " + pwd);
                System.out.println(md5(name + pwd));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }*/

}
