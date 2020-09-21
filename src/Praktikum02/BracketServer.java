package Praktikum02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BracketServer implements CommandExecutor {

    private static final HashMap<String, String> bracketMap = new HashMap<>();
    private ArrayList<String> hallo = null;

    public BracketServer() {
        initBracketMap();
    }

    private void initBracketMap() {
        bracketMap.put(")","(");
        bracketMap.put("]","[");
        bracketMap.put("}","{");
        bracketMap.put(">","<");
        bracketMap.put("*>", "<*");
    }

    public boolean checkBrackets(String arg) {
        List<String> bracketList = getBrackets(arg);
        System.out.println(bracketList);
        ListStack stack = new ListStack();
        boolean wellFormed = true;
        int i = 0;
        while(wellFormed && i < bracketList.size()) {
            String bracket = bracketList.get(i);
            if (bracketMap.containsValue(bracket)) {
                stack.push(bracket);
            } else {
                String poppedBracket = (String) stack.pop();
                if(poppedBracket == null || !poppedBracket.equals(bracketMap.get(bracket))) wellFormed = false;
            }
            i++;
        }
        if(!stack.isEmpty()) wellFormed = false;
        return wellFormed;
    }

    //Later solved with regex [<]/?[(a-z)|\d]+[>]
    private List<String> getBrackets(String arg) {
        List<String> bracketList = new ArrayList<>();
        String pattern = "\\(|\\)|\\{|}|]|\\[|(<\\*?)|(\\*?>)";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(arg);
        while(matcher.find()) {
            bracketList.add(matcher.group());
        }
        return bracketList;
    }

    @Override
    public String execute(String command) throws Exception {
        return String.valueOf(checkBrackets(command));
    }

}
