package forum.web.util;

import com.alibaba.fastjson.JSON;

public class ThreadTest extends Thread{
    
    ThreadTest(String name){
        super(name);
    }
    
    public void run(){
        User user = new User();
        ElasticsearchUtil es = new ElasticsearchUtil();
        es.buildClient("localhost");
        long now1 = 0;
        long now2 = 0;
        int count = (int)(Math.random()*10);
        System.out.println(count);
        now1 = System.currentTimeMillis();
        user.setUser("yxm");
        user.setSex("a b c d e f");
        user.setCount(count);
        String json = JSON.toJSONString(user);
        
        es.addIndex("second", "test", "1", json);
            
        now2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName());
        System.out.println((now2 - now1) + "毫秒");
        es.close();
    }
}
