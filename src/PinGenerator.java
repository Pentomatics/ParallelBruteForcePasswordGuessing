import java.util.Random;

public class PinGenerator {

    public String[] generatePins(int numberOffPins) {
        String pins[] = new String[numberOffPins];

        for (int i = 0; i < pins.length; i++) {
            String pin = "";

            for (int i2 = 0; i2 < 4; i2++) {
                Random rand = new Random();
                int pinValue = rand.nextInt(35);
                pinValue += 1; //number from 1 to 50

                pin += convertNumberToString(pinValue);
            }

            pins[i] = pin;
        }

        return pins;
    }


    public char convertNumberToString(int pinValue) {
        char convertedPinValue;

        if (pinValue < 10) { //1..9
            return Character.forDigit(pinValue, 10);
        }

        switch(pinValue) {
            case 10:
                convertedPinValue = 'a';
                break;
            case 11:
                convertedPinValue = 'b';
                break;
            case 12:
                convertedPinValue = 'c';
                break;
            case 13:
                convertedPinValue = 'd';
                break;
            case 14:
                convertedPinValue = 'e';
                break;
            case 15:
                convertedPinValue = 'f';
                break;
            case 16:
                convertedPinValue = 'g';
                break;
            case 17:
                convertedPinValue = 'h';
                break;
            case 18:
                convertedPinValue = 'i';
                break;
            case 19:
                convertedPinValue = 'j';
                break;
            case 20:
                convertedPinValue = 'k';
                break;
            case 21:
                convertedPinValue = 'l';
                break;
            case 22:
                convertedPinValue = 'm';
                break;
            case 23:
                convertedPinValue = 'n';
                break;
            case 24:
                convertedPinValue = 'o';
                break;
            case 25:
                convertedPinValue = 'p';
                break;
            case 26:
                convertedPinValue = 'q';
                break;
            case 27:
                convertedPinValue = 'r';
                break;
            case 28:
                convertedPinValue = 's';
                break;
            case 29:
                convertedPinValue = 't';
                break;
            case 30:
                convertedPinValue = 'u';
                break;
            case 31:
                convertedPinValue = 'v';
                break;
            case 32:
                convertedPinValue = 'w';
                break;
            case 33:
                convertedPinValue = 'x';
                break;
            case 34:
                convertedPinValue = 'y';
                break;
            case 35:
                convertedPinValue = 'z';
                break;
            default:
                throw new IllegalArgumentException("Irgendwas fehlt hier");
        }

        return convertedPinValue;
    }
}
