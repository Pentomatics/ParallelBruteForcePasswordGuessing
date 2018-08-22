import pr.MakeItSimple;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class PasswordGuessing {

    private String[] names;
    private String[] passwords;
    private char[] passwordAlphabet;


    // Todo why not millis? Wird vom system korrigiert - uhrzeit. Nanos bleiben unbeeinflusst und sind für Zeitmessung gedacht
    private long[] guessingTimesInNanoSeconds;

    public PasswordGuessing(String filename, String passwordAlphabet) {
        readNamesAndPasswordsFromFile(filename);
        this.passwordAlphabet = passwordAlphabet.toCharArray();

        this.guessingTimesInNanoSeconds = new long[names.length];
    }

    public void readNamesAndPasswordsFromFile(String filename) {
        String[] fileRows = MakeItSimple.readStringArray(filename);
        names = new String[fileRows.length];
        passwords = new String[fileRows.length];

        for (int i = 0; i < fileRows.length; i++) {
            String[] splittedRow = fileRows[i].split(",");

            names[i] = splittedRow[0];
            passwords[i] = splittedRow[1];
        }
    }


    public void guessPasswords() {
        LinkedList<PasswordGuesserThread> threads = new LinkedList<>();


        long startNano = System.nanoTime();

        // start all threads
        for (int i = 0; i < names.length; i++) {
            PasswordGuesserThread pgt = new PasswordGuesserThread(names[i], passwords[i], i);
            pgt.start();
            threads.add(pgt);
        }


        // wait for all threads to be finished
        for (PasswordGuesserThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endNano = System.nanoTime();


        long longestGuessingTime = 0;
        long durationOfAllThreads = 0;
        for (long guessingTime : guessingTimesInNanoSeconds) {
            if (guessingTime > longestGuessingTime) {
                longestGuessingTime = guessingTime;
            }

            durationOfAllThreads += guessingTime;
        }

        long durationOfParallelPasswordGuessing = endNano - startNano;

        System.out.println("\nLaufzeit aller parallel laufenden Rate Threads: " + durationOfParallelPasswordGuessing / 1000000 + " ms");
        System.out.println("Laufzeit des längsten Rate Threads: " + longestGuessingTime / 1000000 + " ms");
        System.out.println("Addierte Laufzeit aller Rate Threads: " + durationOfAllThreads / 1000000 + " ms");
        System.out.println("Speedup: " + 1.0 * durationOfAllThreads / durationOfParallelPasswordGuessing);
    }

    public String guessPassword(String name, String encryptedPw) {
        // start value
        String bruteForcePW = "" + passwordAlphabet[0] + passwordAlphabet[0] + passwordAlphabet[0] + passwordAlphabet[0];

        int lastCharacterIndex = passwordAlphabet.length - 1;
        String lastPossiblePassword = "" + passwordAlphabet[lastCharacterIndex] + passwordAlphabet[lastCharacterIndex] + passwordAlphabet[lastCharacterIndex] + passwordAlphabet[lastCharacterIndex];

// Todo, was wenn last possible pw? Pw not found
        try {
            while (!md5(name + bruteForcePW).equals(encryptedPw) && !bruteForcePW.equals(lastPossiblePassword)) {
                bruteForcePW = getNextPassword(bruteForcePW);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//Todo delete
        //System.out.println("The of " + name + " is: " + bruteForcePW);
        return bruteForcePW;
    }


    private static String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return DatatypeConverter.printBase64Binary(md.digest(s.getBytes()));
    }


    private String getNextPassword(String bruteForcePW) {
        char[] password = bruteForcePW.toCharArray();

        if (bruteForcePW.length() <= 0) {
            return "";
        } else {
            int i = bruteForcePW.length();

            do {
                i--;
                password[i] = getNextCharacterOfPasswordAlphabet(password[i]);
            } while(password[i] == passwordAlphabet[0] && i > 0);

            return new String(password);
        }
    }


    private char getNextCharacterOfPasswordAlphabet(char character) {
        int characterIndex = getAlphabetIndexOfCharacter(character);
        char lastAlphabetCharacter = passwordAlphabet[passwordAlphabet.length - 1];

        if (character == lastAlphabetCharacter) {
            return passwordAlphabet[0];
        } else {
            return passwordAlphabet[characterIndex + 1];
        }
    }


    private int getAlphabetIndexOfCharacter(char character) {
        for (int i = 0; i < passwordAlphabet.length; i++) {
            if (character == passwordAlphabet[i]) {
                return i;
            }
        }

        throw new MakeItSimple.PRException("The password alphabet does not contain the character: " + character);
    }




    private class PasswordGuesserThread extends Thread {
        String name;
        String encryptedPW;
        int guessingTimeIndex;

        private PasswordGuesserThread(String name, String encryptedPW, int guessingTimeIndex) {
            this.name = name;
            this.encryptedPW = encryptedPW;
            this.guessingTimeIndex = guessingTimeIndex;
        }

        public synchronized void run() {
            long startNano = System.nanoTime();
            String plainTextPW = guessPassword(name, encryptedPW);
            long endNano = System.nanoTime();

            guessingTimesInNanoSeconds[guessingTimeIndex] = endNano - startNano;
            System.out.println(name + " hat Passwort " + plainTextPW + " Ratedauer: " + guessingTimesInNanoSeconds[guessingTimeIndex] / 1000000 + " ms");
        }
    }
}
