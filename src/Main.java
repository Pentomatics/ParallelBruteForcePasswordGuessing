public class Main {

    class MyThread extends Thread {
        public void run() {
            int i = 0;
            while (true) {
                System.out.println(i++);
            }
        }
    }


    public static final String PINS_FILE_NAME = "src/pins.csv";
    public static final String PASSWORD_ALPHABET = "123456789abcdefghijklmnopqrstuvwxyz";


    public static void main(String[] args) {
        PasswordGuessing pg = new PasswordGuessing(PINS_FILE_NAME, PASSWORD_ALPHABET);
        pg.guessPasswords();







    }

    // Todo delte comment
    // Todo delete PinGenerator
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
