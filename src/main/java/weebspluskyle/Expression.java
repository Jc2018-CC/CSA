package weebspluskyle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    private ArrayList<TokenInfo> tokenInfos;
    private ArrayList<Token> tokens;

    private class TokenInfo {
        public final Pattern regex;
        public final int token;

        public TokenInfo(Pattern regex, int token) {
            this.regex = regex;
            this.token = token;
        }
    }

    public class Token {
        public final int token;
        public final String sequence;

        public Token(int token, String sequence) {
            this.token = token;
            this.sequence = sequence;
        }
    }

    public Expression() {
        tokenInfos = new ArrayList<TokenInfo>();
        tokens = new ArrayList<Token>();
        add("\\(", 1); // open bracket
        add("\\)", 2); // close bracket
        add("sin|cos|tan|log|ln", 3); // function
        add("[+-]", 4); // plus or minus
        add("[*/]", 5); // mult or divide
        add("\\^", 6); // raised
        add("[0-9]+\\.[0-9]+|[0-9]+", 7); // integer number
        add("x|y", 8); // variable
    }

    private void add(String regex, int token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void tokenize(String input) {
        String s = input.replaceAll(" ", "").toLowerCase();
        tokens.clear();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    tokens.add(new Token(info.token, tok));
                    s = m.replaceFirst("");
                    break;
                }
            }
            if (!match) {
                throw new RuntimeException("Unexpected character in input: " + s);
            }
        }
    }

    public double evaluate(double x, double y) {
        ArrayList<Token> l = new ArrayList<>(tokens);

        for (int i = 0; i < l.size(); i++) {
            // STEP 1: REPLACE X AND Y WITH THE NUMBERS PROVIDED IN THIS FUNCTION
            if (l.get(i).token == 8) {
                if (l.get(i).sequence.equals("x")) {
                    l.set(i, new Token(7, "" + x));
                } else {
                    l.set(i, new Token(7, "" + y));
                }
            }

            // STEP 2: ADD 0 IN FRONT OF - SIGN IF THERE IS NOTHING ALREADY IN FRONT OF IT
            if (l.get(i).token == 4 && l.get(i).sequence.equals("-")) {
                if (i - 1 < 0 || (l.get(i - 1).token != 7 && l.get(i - 1).token != 8 && l.get(i - 1).token != 2)) {
                    l.add(i, new Token(7, "0"));
                }
            }

            // STEP 3: CHECK IF FUNCTIONS HAVE A PARENTHESES AFTER, OTHERWISE ERROR
            if (l.get(i).token == 3) {
                if (i + 1 == l.size() || l.get(i + 1).token != 1) {
                    throw new RuntimeException("No parentheses found after function in input");
                }
            }

            // STEP 4: CHECK IF EXPRESSION HAS INVALID OPERATOR LOCATIONS, THROW ERROR
            if (4 <= l.get(i).token && l.get(i).token <= 6) {
                if (i == 0) {
                    throw new RuntimeException("No number found preceding operator in input");
                }
                if (i + 1 == l.size()) {
                    throw new RuntimeException("No number or variable found after operator in input");
                }
                int prevToken = l.get(i - 1).token;
                int nextToken = l.get(i + 1).token;

                if (prevToken != 7 && prevToken != 8 && prevToken != 2) {
                    throw new RuntimeException("No number found preceding operator in input");
                }
                if (nextToken == 2 || (4 <= nextToken && nextToken <= 6)) {
                    throw new RuntimeException("No number or variable found after operator in input");
                }
            }
        }

        // STEP 4: SPAGHETTI SPAGHETTI SPAGHETTI SPAGHETTI SPAGHETTI SPAGHETTI
        return evaluateRecurs(l);
    }

    private double evaluateRecurs(List<Token> l) {
        for (int i = 0; i < l.size(); i++) {
            // PARENTHESES and also FUNCTIONS
            if (l.get(i).token == 1) {
                // find closing parentheses
                int close = findClose(i + 1, l);
                if (close == -1) {
                    throw new RuntimeException("Parentheses not closed properly");
                }

                // evaluate the inside
                double inner = evaluateRecurs(l.subList(i + 1, close));

                // function logic
                if (i - 1 >= 0 && l.get(i - 1).token == 3) {
                    String fn = l.get(i - 1).sequence;
                    if (fn.equals("sin")) {
                        inner = Math.sin(inner);
                    } else if (fn.equals("cos")) {
                        inner = Math.cos(inner);
                    } else if (fn.equals("tan")) {
                        inner = Math.tan(inner);
                    } else if (fn.equals("log")) {
                        inner = Math.log10(inner);
                    } else if (fn.equals("ln")) {
                        inner = Math.log(inner);
                    }
                    l.remove(i - 1);
                    i--;
                }
                close = findClose(i + 1, l);

                // replace the parentheses with the number
                ArrayList<Token> newL = new ArrayList<>(l.subList(0, i));
                newL.add(new Token(7, "" + inner));
                if (l.size() > close + 1) {
                    newL.addAll(l.subList(close + 1, l.size()));
                }
                l = newL;
            }
        }

        // EXPONENTS
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i).token == 6) {
                double left = Double.parseDouble(l.get(i - 1).sequence);
                double right = Double.parseDouble(l.get(i + 1).sequence);
                l.set(i - 1, new Token(7, "" + Math.pow(left, right)));
                l.remove(i + 1);
                l.remove(i);
                i--;
            }
        }

        // MULT/DIV
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i - 1).token == 7 && l.get(i).token == 7) {
                double left = Double.parseDouble(l.get(i - 1).sequence);
                double right = Double.parseDouble(l.get(i).sequence);
                l.set(i - 1, new Token(7, "" + left * right));
                l.remove(i);
                i--;
            }

            if (l.get(i).token == 5) {
                double left = Double.parseDouble(l.get(i - 1).sequence);
                double right = Double.parseDouble(l.get(i + 1).sequence);
                double ans;
                if (l.get(i).sequence.equals("*")) {
                    ans = left * right;
                } else {
                    ans = left / right;
                }
                l.set(i - 1, new Token(7, "" + ans));
                l.remove(i + 1);
                l.remove(i);
                i--;
            }
        }

        // ADD/SUB
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).token == 4) {
                double left = Double.parseDouble(l.get(i - 1).sequence);
                double right = Double.parseDouble(l.get(i + 1).sequence);
                double ans;
                if (l.get(i).sequence.equals("+")) {
                    ans = left + right;
                } else {
                    ans = left - right;
                }
                l.set(i - 1, new Token(7, "" + ans));
                l.remove(i + 1);
                l.remove(i);
                i--;
            }
        }

        return Double.parseDouble(l.get(0).sequence);
    }

    private int findClose(int s, List<Token> l) {
        int count = 0;
        for (int i = s; i < l.size(); i++) {
            if (l.get(i).token == 1) {
                count++;
            }
            if (l.get(i).token == 2) {
                if (count == 0) {
                    return i;
                } else {
                    count--;
                }
            }
        }
        return -1;
    }
}
