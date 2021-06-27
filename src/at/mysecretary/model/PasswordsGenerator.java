package at.mysecretary.model;

public class PasswordsGenerator {

    private boolean letter;
    private boolean number;
    private boolean specialChar;
    private double length;
    private TextField result;
    private static char[] alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz".toCharArray();
    private static char[] specialChars = "!#$%&@€§".toCharArray();
    private static char[] numbers = "0123456789".toCharArray();
    private static int randomArr = 0;
    ArrayList<ImageView> stars = new ArrayList<>();

    public PasswordsGenerator(boolean letter, boolean specialChar, boolean number, int length) {
        this.letter = letter;
        this.number = number;
        this.specialChar = specialChar;
        this.length = length;
        this.result = result;
        stars.add(img_star1);
        stars.add(img_star2);
        stars.add(img_star3);
        stars.add(img_star4);
        stars.add(img_star5);
        int randomNum = 0;

        checkSafety(letter, number, specialChar, length, stars);

        result.setText("");

        for(int i = 0; i < (int)length; i++){
            boolean truth = false;
            do{
                randomArr = (int) (Math.random() * 3);
                if(randomArr == 0 & letter == true){
                    int max = alphabet.length - 1;
                    int min = 1;
                    int range = max - min + 1;
                    randomNum = (int)(Math.random() * range)+min;
                    result.setText(result.getText() + alphabet[randomNum]);
                    break;
                }else if(randomArr == 1 & specialChar == true){
                    int max = specialChars.length - 1;
                    int min = 1;
                    int range = max - min + 1;
                    randomNum = (int)(Math.random() * range)+min;
                    result.setText(result.getText() + specialChars[randomNum]);
                    break;
                }else if(randomArr == 2 & number == true){
                    int max = numbers.length - 1;
                    int min = 1;
                    int range = max - min + 1;
                    randomNum = (int)(Math.random() * range)+min;
                    result.setText(result.getText() + numbers[randomNum]);
                    break;
                }
                if(!letter & !specialChar & !number){
                    break;
                }
            }while(!truth);
        }
    }

    public void checkSafety(boolean letter, boolean number, boolean specialChar, double length, ArrayList<ImageView> stars) throws FileNotFoundException {
        int range = 0;

        //Diese Schleife braucht man um die Imageviews wieder zurückzusetzen
        for(int counter = 0; counter < 5; counter++){
            stars.get(counter).setImage(null);
        }

        if(letter){
            range++;
        }
        if(number){
            range++;
        }
        if(specialChar){
            range++;
        }
        if((int)length >= 15){
            range++;
        }
        if((int)length == 25){
            range++;
        }

        Image image = new Image(new FileInputStream("src/at.mysecretary.images/Star.png"));

        for(int counter = 0; counter < range; counter++){
            stars.get(counter).setImage(image);
        }

    }
}