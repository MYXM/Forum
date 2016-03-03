package forum.web.util;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.alibaba.fastjson.JSON;

public class Test {
    
    private static ElasticsearchUtil es = new ElasticsearchUtil();
    
    static{
        es.buildClient("localhost");
    }
    
    public static void addNotes(String index, String type, String id, String json){
        es.addIndex(index, type, id, json);
    }
    
    public static List<String> clickNotes(String index, String type, QueryBuilder postFilter){
        return es.searchDoc(index, type, postFilter);
    }
    
    public static void main(String[] args) {
//        User user = new User();
//        user.setUser("test");
//        user.setSex("a b c d e f");
//        String json = JSON.toJSONString(user);
//        Test.addNotes("second", "test", "asd", json);
//        List<String> resultList = Test.clickNotes("second", "test", QueryBuilders.matchQuery("sex", "b"));
//        System.out.println(resultList.toString());
        String a = new String("good");
        String b = a;
        System.out.println(b);
    }
}
