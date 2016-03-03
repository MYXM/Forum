package forum.web.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    
    private static Jedis jedis = null;
    
    public static void getConnection(String host){
        jedis = new Jedis(host);
    }
    
    public static void addData(String key, String value){
        jedis.set(key, value);
    }
    
    public static String getData(String key){
        return jedis.get(key);
    }
    
    public static void saveData(){
        jedis.save();
    }
    
    public static void main(String[] args) {
        RedisUtil.getConnection("192.168.10.92");
//        RedisUtil.addData("test", "0");
//        RedisUtil.saveData();
        System.out.println(RedisUtil.getData("test"));
    }
}
