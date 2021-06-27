package at.mysecretary.model;

public class PasswordsGenerator {

    private boolean letter;
    private boolean number;
    private boolean specialChar;
    private int length;
    private static final char[] alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz".toCharArray();
    private static final char[] specialChars = "!#$%&@€§".toCharArray();
    private static final char[] numbers = "0123456789".toCharArray();

    public PasswordsGenerator(boolean letter, boolean specialChar, boolean number, int length) {
        this.letter = letter;
        this.number = number;
        this.specialChar = specialChar;
        this.length = length;
    }

    public String generatePassword(boolean isLetter, boolean isSpecialCharacter, boolean isNumber, int passwdLength) {
        StringBuilder password = new StringBuilder();
        int type = (int) (Math.random() * 3);

        for (int i = 0; i < passwdLength; i++) {

            if (isLetter && !isSpecialCharacter && !isNumber) {
                password.append(generateRandomLetter());
            }
            else if (!isLetter && isSpecialCharacter && !isNumber) {
                password.append(generateRandomSpecialCharacter());
            }
            else if (!isLetter && !isSpecialCharacter && isNumber) {
                password.append(generateRandomNumber());
            }
            else if (isLetter&& isSpecialCharacter && !isNumber) {
                type = (int) (Math.random() * 2);
                if (type == 0) {
                    password.append(generateRandomLetter());
                }
                else if (type == 1) {
                    password.append(generateRandomSpecialCharacter());
                }
            }
            else if (!isLetter&& isSpecialCharacter && isNumber) {
                type = (int) (Math.random() * 2);
                if (type == 0) {
                    password.append(generateRandomSpecialCharacter());
                }
                else if (type == 1) {
                    password.append(generateRandomNumber());
                }
            }
            else if (isLetter && !isSpecialCharacter && isNumber) {
                type = (int) (Math.random() * 2);
                if (type == 0) {
                    password.append(generateRandomLetter());
                }
                else if (type == 1) {
                    password.append(generateRandomNumber());
                }
            }
            if (isLetter && isSpecialCharacter && isNumber) {
                type = (int) (Math.random() * 3);
                if (type == 0) {
                    password.append(generateRandomLetter());
                }
                else if (type == 1) {
                    password.append(generateRandomSpecialCharacter());
                }
                else if (type == 2) {
                    password.append(generateRandomNumber());
                }
            }
            if (!isLetter && !isSpecialCharacter && !isNumber) {
                return "Nothing chosen!";
            }
        }
        return password.toString();
    }

    public char generateRandomLetter() {
        int randomLetter;
        int max = (alphabet.length) - 1;
        int min = 1;
        int range = max - min + 1;
        randomLetter = ((int)(Math.random() * range)+min);
        return alphabet[randomLetter];
    }

    public char generateRandomSpecialCharacter() {
        int randomSpecialChar;
        int max = specialChars.length - 1;
        int min = 1;
        int range = max - min + 1;
        randomSpecialChar = ((int)(Math.random() * range)+min);
        return specialChars[randomSpecialChar];
    }

    public char generateRandomNumber() {
        int randomNumber;
        int max = numbers.length - 1;
        int min = 1;
        int range = max - min + 1;
        randomNumber = ((int)(Math.random() * range)+min);
        return numbers[randomNumber];
    }

}