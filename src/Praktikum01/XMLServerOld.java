package Praktikum01;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// [<]\\?[(a-z)|\d]+[>]

public class XMLServerOld implements CommandExecutor{

    public boolean checkWellFormed(String arg) {
         List<String> tagList = findTags(arg);
         boolean wellFormed = true;
         if(tagList.size() % 2 > 0) wellFormed = false;
         ListStack stack = new ListStack();
         int i = 0;
         while(wellFormed && i < tagList.size()) {
             String tag = tagList.get(i);
             if(tag.contains("/")) {
                 String poppedTag = (String) stack.pop();
                 tag = tag.replace("/", "");
                 if(!tag.equals(poppedTag)) wellFormed = false;
             }else {
                 stack.push(tag);
             }
             i++;
         }
         if(wellFormed && !stack.isEmpty()) wellFormed = false;
         return wellFormed;
    }

    // regex for tag: starts with "<",
    // then one or no "/",
    // then at least one letter or digit
    // and ends with ">"
    // -> anything else is ignored! (incl. <b/>)
    private List<String> findTags(String arg) {
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
