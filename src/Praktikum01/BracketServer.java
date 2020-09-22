package Praktikum01;

import java.util.HashMap;

public class BracketServer implements CommandExecutor{

    private static final HashMap<Character, Character> bracketMap = new HashMap<>();

    public BracketServer() {
        initBracketMap();
    }

    private void initBracketMap() {
        bracketMap.put(')','(');
        bracketMap.put(']','[');
        bracketMap.put('}','{');
    }

    public boolean checkBrackets(String arg) {
        //arg = removeIrrelevantChar(arg);
        arg = arg.replaceAll("[^\\[\\]{}()]","");
        ListStack stack = new ListStack();
        boolean success = true;
        if(arg.length() % 2 > 0) success = false;
        int i = 0;
        while(success && i < arg.length()) {
            char bracket = arg.charAt(i);
            if (bracketMap.containsValue(bracket)) {
                stack.push(bracket);
            } else if (bracketMap.containsKey(bracket)) {
                char poppedChar = (char) stack.pop();
                if(poppedChar != bracketMap.get(bracket)) success = false;
            }
            i++;
        }
        if(!stack.isEmpty()) success = false;
        return success;
    }

    //Later solved with regex
    /*
    private String removeIrrelevantChar(String arg) {
        StringBuilder sb = new StringBuilder();
        for(char character : arg.toCharArray()) {
            if(bracketMap.containsKey(character) || bracketMap.containsValue(character)) {
                sb.append(character);
            }
        }
        return sb.toString();
    }
     */

    @Override
    public String execute(String command) throws Exception {
        return String.valueOf(checkBrackets(command));
    }

}
