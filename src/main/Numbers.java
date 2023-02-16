package src.main;

public class Numbers {
    public static boolean applyFunctions(String[] functionsNames, long number) {
        boolean result = true;
        for (int i = 2; i < functionsNames.length; i++) {
            boolean negate;
            String functionName;
            if (functionsNames[i].substring(0, 1).equalsIgnoreCase("-")) {
                negate = true;
                functionName = functionsNames[i].substring(1);
            } else {
                negate = false;
                functionName = functionsNames[i];
            }

            boolean functionResult;
            switch (functionName.toLowerCase()) {
                case "even" -> functionResult = isEven(number);
                case "odd" -> functionResult = !isEven(number);
                case "buzz" -> functionResult = isBuzz(number);
                case "duck" -> functionResult = isDuck(number);
                case "palindromic" -> functionResult = isPalindrome(number);
                case "gapful" -> functionResult = isGapful(number);
                case "spy" -> functionResult = isSpy(number);
                case "sunny" -> functionResult = isSunny(number);
                case "square" -> functionResult = isSquare(number);
                case "jumping" -> functionResult = isJumping(number);
                case "happy" -> functionResult = isHappy(number);
                case "sad" -> functionResult = isSad(number);
                default -> {
                    return false;
                }
            }

            result = result && (negate != functionResult);
        }
        return result;
    }

    public static String describeNumber(long number) {

        return number + " is " +
                (isEven(number) ? "even" : "odd") +
                (isBuzz(number) ? ", buzz" : "") +
                (isDuck(number) ? ", duck" : "") +
                (isPalindrome(number) ? ", palindromic" : "") +
                (isGapful(number) ? ", gapful" : "") +
                (isSpy(number) ? ", spy" : "") +
                (isSunny(number) ? ", sunny" : "") +
                (isSquare(number) ? ", square" : "") +
                (isJumping(number) ? ", jumping" : "") +
                (isHappy(number) ? ", happy" : "") +
                (isSad(number) ? ", sad" : "");
    }

    public static String getProperties(long number) {
        return "Properties of " + number +
                "\neven: " + (isEven(number) ? "true" : "false") +
                "\nodd: " + (!isEven(number) ? "true" : "false") +
                "\nbuzz: " + (isBuzz(number) ? "true" : "false") +
                "\nduck: " + (isDuck(number) ? "true" : "false") +
                "\npalindromic: " + (isPalindrome(number) ? "true" : "false") +
                "\ngapful: " + (isGapful(number) ? "true" : "false") +
                "\nspy: " + (isSpy(number) ? "true" : "false") +
                "\nsunny: " + (isSunny(number) ? "true" : "false") +
                "\nsquare: " + (isSquare(number) ? "true" : "false") +
                "\njumping: " + (isJumping(number) ? "true" : "false") +
                "\nhappy: " + (isHappy(number) ? "true" : "false") +
                "\nsad: " + (isSad(number) ? "true" : "false");
    }

    public static boolean isHappy(long number) {
        long currentNumber = number;
        int maxIterations = 100;
        int iterationsCount = 0;

        while(iterationsCount < maxIterations) {
            currentNumber = sumOfDigitsSquares(currentNumber);
            if (currentNumber == 1) {
                return true;
            }
            iterationsCount += 1;
        }
        return false;
    }

    public static long sumOfDigitsSquares(long number) {
        long sum = 0;

        while(number != 0) {
            sum += (number % 10) * (number % 10);
            number /= 10;
        }

        return sum;
    }

    public static boolean isSad(long number) {
        return !isHappy(number);
    }

    public static boolean isJumping(long number) {
        int previousDigit = (int) number % 10;
        number /= 10;

        while (number != 0) {
            if (Math.abs(number % 10 - previousDigit) > 1 || Math.abs(number % 10 - previousDigit) == 0) {
                return false;
            }
            previousDigit = (int) (number % 10);
            number /= 10;
        }
        return true;
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isBuzz(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    public static boolean isDuck(long number) {
        return String.valueOf(number).contains("0");
    }

    public static boolean isPalindrome(long number) {
        String numberString = String.valueOf(number);
        int length = numberString.length();
        for (int i = 0; i < length / 2; i++) {
            if (numberString.charAt(i) != numberString.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGapful(long number) {
        if (number < 100) return false;

        String numberString = String.valueOf(number);
        int firstDigit = Character.getNumericValue(numberString.charAt(0));
        int lastDigit = Character.getNumericValue(numberString.charAt(numberString.length() - 1));
        int concNum = firstDigit * 10 + lastDigit;
        return numberString.length() > 2 && number % concNum == 0;
    }

    public static boolean isSpy(long number) {
        String numberString = String.valueOf(number);
        int sumOfDigits = 0;
        int productOfDigits = 1;
        for (int i = 0; i < numberString.length(); i++) {
            int digit = Character.getNumericValue(numberString.charAt(i));
            sumOfDigits += digit;
            productOfDigits *= digit;
        }
        return sumOfDigits == productOfDigits;
    }

    public static boolean isSquare(long number) {
        return Math.floor(Math.sqrt(number)) == Math.sqrt(number);
    }

    public static boolean isSunny(long number) {
        return isSquare(number + 1);
    }
}