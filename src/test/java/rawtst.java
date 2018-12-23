import java.util.regex.*;

public class rawtst {

    public static void main(String args[]){

        String raw = "others[1]";
        Pattern r = Pattern.compile("(?<=\\[)(\\d+)(?=\\]$)");
        Matcher m = r.matcher(raw);
        if(m.find()) {
            System.out.println(m.group());
        }
        //String nm = r.matcher(raw).group();


//        Matcher m = r.matcher(raw);

    }

}