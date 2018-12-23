
import textutil.utiljson;

public class utiljsontest {

    public static void main(String args[]){

        String json = "{\"name\":{\"first\":\"Alice\",\"last\":\"perl\"},\"age\":17,\"eutime\":\"2018-12-23 14:40\",\"favorite\":[\"reading\",\"sports\",\"fast food\"],\"others\":[{\"level\":78,\"strength\":34,\"intelligence\":84},\"magic\"]}";

        utiljson uj = new utiljson(json);

        //System.out.println(uj.jsobject);

        String r = uj.getitall("others[1].intelligence");

        System.out.println(r);

    }
}
