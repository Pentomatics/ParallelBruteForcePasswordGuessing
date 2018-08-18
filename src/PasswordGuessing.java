import pr.MakeItSimple;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordGuessing {

    private String[] names;
    private String[] passwords;
    private char[] passwordAlphabet;

    public PasswordGuessing(String filename, String passwordAlphabet) {
        readNamesAndPasswordsFromFile(filename);
        this.passwordAlphabet = passwordAlphabet.toCharArray();
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
        for (int i = 0; i < names.length; i++) {
            guessPassword(names[i], passwords[i]);
        }
    }

    public void guessPassword(String name, String encryptedPw) {
        // start value
        String bruteForcePW = "" + passwordAlphabet[0] + passwordAlphabet[0] + passwordAlphabet[0] + passwordAlphabet[0];

        int lastCharacterIndex = passwordAlphabet.length - 1;
        String lastPossiblePassword = "" + passwordAlphabet[lastCharacterIndex] + passwordAlphabet[lastCharacterIndex] + passwordAlphabet[lastCharacterIndex] + passwordAlphabet[lastCharacterIndex];


        try {
            while (!md5(name + bruteForcePW).equals(encryptedPw) && !bruteForcePW.equals(lastPossiblePassword)) {
                bruteForcePW = getNextPassword(bruteForcePW);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("The password was: " + bruteForcePW);
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
}
