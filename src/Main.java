import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        String[] operand = {"+", "-", "*", "/"};

        Scanner scanner = new Scanner(System.in);
        String abc = scanner.nextLine();
        String sign = getMatchingOperand(abc, operand);
        if (sign != null) {
            if (getResult(abc, sign, roman) != null) {
                System.out.println(getResult(abc, sign, roman));
            }
        }
        else {
            System.out.println("Error: wrong format");
        }
    }

    private static String getMatchingOperand(String line, String[] symbols) {
        for (String symbol : symbols) {
            if (line.contains(symbol)) {
                return symbol;
            }
        }
        return null;
    }
    public static void checkCrossover(int check) throws CrossoverException {
        if (check == 1) {
            throw new CrossoverException("Error: Arabic and Roman numbers crossover");
        }
    }

    private static String getResult (String abc, String sign, String[] roman) {
        String[] result = abc.split("["+sign+"]");
        if (result.length > 2) {
            System.out.println("Error: 2 numbers only");
        }
        else {
            boolean done = false;
            boolean romanResult = false;
            int b = 0;
            int c = 1;
            int counter = 0;
            for (String digit : result) {
                String trimDigit = digit.trim();
                if (Arrays.asList(roman).contains(trimDigit)){
                    romanResult = true;
                    counter++;
                    trimDigit = String.valueOf(Arrays.asList(roman).indexOf(trimDigit)+1);
                }
                try {
                    int a = Integer.parseInt(trimDigit);

                    if (a > 0 && a <= 10) {
                        if (sign.equals("+")) {
                            b += a;
                        }
                        if (sign.equals("-")) {
                            b = - b - a;
                        }
                        if (sign.equals("*")) {
                            b = a*c;
                            c = b;
                        }
                        if (sign.equals("/")) {
                            b = c/a;
                            c = a;
                        }
                        done = true;
                    }
                    else {
                        System.out.println("Error: only numbers from 1 to 10");
                        done = false;
                        break;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Error: integers only");
                    done = false;
                    break;
                }
            }
            try {
                checkCrossover(counter);
                if (done){
                    if (romanResult){
                        return roman[b-1];
                    }
                    else {
                        return String.valueOf(b);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException | CrossoverException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}