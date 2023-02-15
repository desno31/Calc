public class Main {
       static int romanToInt(String s){
           switch (s){
               case "I": return 1;
               case "II": return 2;
               case "III": return 3;
               case "IV": return 4;
               case "V": return 5;
               case "VI": return 6;
               case "VII": return 7;
               case "VIII": return 8;
               case "IX": return 9;
               case "X": return 10;
               default: return 0;
           }
       }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    static int calculations(int a, int b, char c) {
        if (a < 1 || a > 10 || b < 1 || b > 10)
            return -10;
        switch (c) {
            case 47:
                return a / b;
            case 42:
                return a * b;
            case 43:
                return a + b;
            case 45:
                return a - b;
            default:
                return -10;
        }
    }

    static byte validOper(String s) {
        byte firstNum = 0; // 0 - арабские, 1 - римские.
        byte secondNum = 0;
        if (s.charAt(0) > 57)
            firstNum = 1;
        if (s.charAt(s.length() - 1) > 57)
            secondNum = 1;
        if (firstNum != secondNum)
            return -1;
        else
            return firstNum;
    }

    static boolean validInput(String s){
        char [] c = {' ','*','+','-','/','0','1','2','3','4','5','6','7','8','9','I','V','X'};
        int k = s.length();
        for (int i = 0; i < s.length(); i++) {
            int j = 0;
            while (j < c.length) {
                if (s.charAt(i) == c[j]) {
                    k--;
                    break;
                }
                j++;
            }
        }
        if (k > 0)
            return false;
        return true;
    }

    public static String calc(String primer) {
        String result = "throws Exception";
        if (!validInput(primer))
            return result;
        primer = primer.replace(" ", "");
        byte valid = validOper(primer);
        if (valid == -1)
            return result;  // разная система цифр.
        char operation = 0;  //оператор
        int k = 0;//индекс оператора
        int chek = 0;
        for (int i = 0; i < primer.length(); i++) {
            if (primer.charAt(i) < 48) {
                operation = primer.charAt(i);
                k = i;
                chek++;  // проверка на количесто операторов
            }
        }
        if (chek > 1 || chek == 0 || k == primer.length() - 1 || k == 0)
            return result;
        String firstNum = primer.substring(0, k);
        String secondNum = primer.substring(k + 1);
        if (valid == 0) {
                result = String.valueOf(calculations(Integer.parseInt(firstNum), Integer.parseInt(secondNum), operation));
                if (!result.equals("-10"))
                    return result;
                else
                    return "throws Exception";
        }
        else {
        if (romanToInt(firstNum) == 0 || romanToInt(secondNum) == 0)
            return result;
        int value = calculations(romanToInt(firstNum),romanToInt(secondNum), operation);
        if (value > 0)
        result = intToRoman(value);
        }
        return result;
    }
}
