package by.me;

/*  форма Бекуса-Наура -
 *      Метод рекурсивного спуска
 *      a - b * (c + d) + e - d + f / g
 *   E   ->  T + E   |   T - E   |   T      E-> T+-T+-...;
 *   T   ->  F * T   |   F / T   |   F      T-> F* /F* /...;
 *   F   ->  num     |   (E)
 */

public class RecursiveCalculator {

    private String[] tokens;
    private int pos;

    public RecursiveCalculator(String[] tokens) {
        this.tokens = tokens;
    }

    public static void main(String[] args) {
        String expression = "2 - 3 * ( 4 + 5 ) + 6 - 7 + 9 / 3";
        RecursiveCalculator calc = new RecursiveCalculator(expression.split(" "));
        System.out.println(calc.parse());
    }

    private double parse() {
        return expression();
    }

    private double expression() {
        Double first = term();
        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                pos++;
            }

            Double second = term();
            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }

    private double term() {
        Double first = factor();
        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                pos++;
            }

            Double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else {
                if (second != 0) {
                    first /= second;
                } else {
                    throw new IllegalStateException("Delimeter is 0");
                }
            }
        }
        return first;
    }

    private double factor() {
        String next = tokens[pos];
        Double result;
        if (next.equals("(")) {
            pos++;
            result = expression();
            String closingBracket = tokens[pos];
            if (closingBracket.equals(")")) {
                pos++;
                return result;
            }
            throw new IllegalStateException("')' expected but" + closingBracket + " found");
        }
        pos++;
        return Double.parseDouble(next);
    }


}
