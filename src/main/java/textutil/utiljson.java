package textutil;

import java.util.regex.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class utiljson {

    private JSONObject jsobject;

    public utiljson(String json){

        this.jsobject = (JSONObject) JSONValue.parse(json);

    }

    public String getitall(String key){
        Object r = getitall(jsobject, key);
        if(r!=null){
            return r.toString();
        }else{
            return "";
        }
    }

    private Object getitall(JSONObject jo, String key){

        if (key.equals("")){
            System.out.println(key);
            return jo;
        }
        String[] keys = key.split("\\.");
        int times = keys.length;

        if (times != 1){
            key = key.substring(keys[0].length()+1);
        }else{
            key = "";
        }

        // 匹配key是否\[\d+\]
        String k;
        String reg = "^.+\\[\\d+\\]$";
        int idx = 0;
        if (Pattern.matches(reg, keys[0])){
            // 目标是Array, 首先拿出Array所在Key
            k = keys[0].replaceFirst("\\[\\d+\\]$","");
            // 这里匹配拿出index放到变量idx中
            Pattern r = Pattern.compile("(?<=\\[)(\\d+)(?=\\]$)");
            Matcher m = r.matcher(keys[0]);
            if (m.find()) {
                String nm = m.group();
                idx = Integer.parseInt(nm);
            }
            else{
                idx = -1;
            }
        }else{
            // 目标是普通的值
            k = keys[0];
            idx = -1;
        }

        // 判断是否存在该key
        if(jo.containsKey(k)) {
            Object sonjosrc = jo.get(k);
            if(sonjosrc instanceof JSONArray && idx>=0){
                JSONArray sonjo2 = (JSONArray) sonjosrc;
                Object sonjo = sonjo2.get(idx);
                if (sonjo instanceof JSONObject){
                    JSONObject sonjo3 = (JSONObject) sonjo;
                    return getitall(sonjo3, key);
                }else{
                    if (key.equals("")) {
                        return sonjo;
                    }else{
                        return null;
                    }
                }
            }
            else if(sonjosrc instanceof JSONObject) {
                JSONObject sonjo = (JSONObject) sonjosrc;
                return getitall(sonjo, key);
            }else{
                if(jo.containsKey(keys[0])) {
                    return jo.get(keys[0]);
                }else{
                    return null;
                }
            }
        }else{
            return null;
        }


    }

}
