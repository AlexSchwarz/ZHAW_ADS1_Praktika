package Praktikum01;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLServer implements CommandExecutor{

    public boolean checkWellFormed(String arg) {
        List<String> tagList = getTokens(arg);
        ListStack stack = new ListStack();
        boolean wellFormed = true;
        int i = 0;
        while(wellFormed && i < tagList.size()) {
            String tag = tagList.get(i);
            System.out.println("looking at: " + tag);
            if(!tag.contains("/")) {
                System.out.println("push: " + tag);
                stack.push(tag);
            }else {
                String poppedTag = (String) stack.pop();
                tag = tag.replace("/", "");
                System.out.println("popped: " + poppedTag);
                if(!tag.equals(poppedTag)) wellFormed = false;
            }
            i++;
        }
        if(!stack.isEmpty()) wellFormed = false;
        return wellFormed;
     }

    private List<String> getTokens(String arg) {
        List<String> tagList = new ArrayList<>();
        String pattern = "[<]/?[(a-z)|\\d]+[>]";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(arg);
        while(matcher.find()) {
            tagList.add(matcher.group());
        }
        return tagList;
    }

    @Override
    public String execute(String command) throws Exception {
        return String.valueOf(checkWellFormed(command));
    }
}
