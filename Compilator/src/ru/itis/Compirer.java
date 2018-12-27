package ru.itis;


import java.util.ArrayList;
import java.util.List;

public class Compirer {
    private ArrayList<Variable> variables = new ArrayList<>();
    private int operation = 0;

    public void analyze(String input) throws SyntaxException {
        String lines[] = input.split(";");
        for (int i = 0; i < lines.length; i++) {
            lines[i] += ";";
            try {
            int state = 0;
            int j = 0;
            Variable variable = null;
            int tempValue = 0;
            boolean isContinue = true;
            while (isContinue) {
                switch (state) {
                    case 0:
                        if (lines[i].charAt(j) == ' ') {
                            j++;
                            break;
                        }
                        if (lines[i].charAt(j) >= 'A' && lines[i].charAt(j) <= 'Z' &&
                                lines[i].charAt(j + 1) >= '0' && lines[i].charAt(j + 1) <= '9') {
                            String temp = "" + lines[i].charAt(j) + lines[i].charAt(j + 1);
                            variable = process(temp).get(0);
                            j += 2;
                            state++;
                        } else
                            throw new SyntaxException();
                        break;
                    case 1:
                        if (lines[i].charAt(j) == ':' && lines[i].charAt(j + 1) == '=') {
                            j += 2;
                            state++;
                        } else {
                            throw new SyntaxException();
                        }
                        break;
                    case 2:
                        if (lines[i].charAt(j) >= 'A' && lines[i].charAt(j) <= 'Z' &&
                                lines[i].charAt(j + 1) >= '0' && lines[i].charAt(j + 1) <= '9') {
                            String temp = "" + lines[i].charAt(j) + lines[i].charAt(j + 1);
                            Variable tempVariable = getVariable(temp);
                            if (tempVariable == null) {
                                throw new InitializeException();
                            }
                            tempValue = operating(tempValue, tempVariable.value);
                            j += 2;
                            state++;
                            break;
                        } else if (lines[i].charAt(j) >= '0' && lines[i].charAt(j) <= '9') {
                            int t = lines[i].charAt(j) - '0';
                            j++;
                            while (lines[i].charAt(j) >= '0' && lines[i].charAt(j) <= '9') {
                                t *= 10;
                                t += lines[i].charAt(j) - '0';
                                j++;
                            }
                            tempValue = operating(tempValue, t);
                            state++;
                            break;
                        } else
                            throw new SyntaxException();
                    case 3:
                        switch (lines[i].charAt(j)) {
                            case ';':
                                variable.value = tempValue;
                                isContinue = false;
                                break;
                            case '+':
                                operation = 0;
                                state = 2;
                                j++;
                                break;
                            case '-':
                                operation = 1;
                                state = 2;
                                j++;
                                break;
                            case '*':
                                operation = 2;
                                state = 2;
                                j++;
                                break;
                            case '/':
                                operation = 3;
                                state = 2;
                                j++;
                                break;
                            default:
                                throw new SyntaxException();
                        }
                        break;
                }
            }
            }catch (IndexOutOfBoundsException e){
                throw new SyntaxException();
            }
        }
        int f = 0;
    }

    private int operating(int a, int b){
        switch (operation){
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return a / b;
            default:
                throw new SyntaxException();
        }
    }

    private Variable getVariable(String input) {
        Variable result = null;
        for (Variable temp : variables) {
            if (temp.name.equals(input)) {
                result = temp;
            }
        }
        return result;
    }

    public List<Variable> process(String input) {
        List<Variable> list = new ArrayList<>();
        Variable temp = getVariable(input);
        if(temp == null){
            Variable t = new Variable(input, 0);
            variables.add(t);
            list.add(t);
        }else {
            list.add(temp);
        }
        return list;
    }

    public ArrayList<Variable> getVariables(){
        return this.variables;
    }
}
