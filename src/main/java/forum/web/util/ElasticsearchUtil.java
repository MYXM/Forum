package forum.web.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.alibaba.fastjson.JSON;

public class ElasticsearchUtil {
    
    private static Client client = null;
    
    /**
     * 创建client
     * @param host(ip地址)
     */
    public void buildClient(String host){
        try {
            client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 添加单个索引
     * @param index（索引）
     * @param type（类型）
     * @param id
     * @param json（json对象字符串）
     * @return
     */
    public IndexResponse addIndex(String index, String type, String id, String json){
        IndexResponse response = client.prepareIndex(index, type)
                .setId(id)
                .setSource(json)
                .get();
        return response;
    }
    
    /**
     * 删除单个文档
     * @param index
     * @param type
     * @param id
     * @return
     */
    public DeleteResponse deleteDoc(String index, String type, String id){
        DeleteResponse response = client.prepareDelete(index, type, id).get();
        return response;
    }
    
    public List<String> searchDoc(String index, String type, QueryBuilder postFilter){
        
        List<String> resultList = new ArrayList<String>();
        
        SearchResponse response = client.prepareSearch(index)
              .setTypes(type)
              .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//              .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
              .setPostFilter(postFilter)     // Filter
              .setFrom(0).setSize(10000).setExplain(true)
              .execute()
              .actionGet();
        SearchHit[] searchHits = response.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            resultList.add(searchHit.getSourceAsString());
        }
        return resultList;
    }
    
    public void close(){
        if(client != null){
            client.close();
        }
    }
    
    public static void main(String[] args) {
        //插入10000条用时472646毫秒
        //查询10000条用时0.5秒
        
        User user = new User();
        ElasticsearchUtil es = new ElasticsearchUtil();
        es.buildClient("localhost");
//        long now1 = 0;
//        long now2 = 0;
//        now1 = System.currentTimeMillis();
//        for(int i = 0; i<10; i++){
            user.setUser("ss");
            user.setSex("a b c d e f");
//            user.setCount(i);
            String json = JSON.toJSONString(user);
            
//            es.addIndex("third", "test", "asd", json);
            
//        }
//        now2 = System.currentTimeMillis();
        
        long now1 = System.currentTimeMillis();
        List<String> resultList =  es.searchDoc("first", "test", QueryBuilders.matchQuery("sex", "a"));
//        for (String string : resultList) {
//            System.out.println(string);
//        }
        long now2 = System.currentTimeMillis();
        System.out.println(resultList.size());
        System.out.println((now2 - now1) + "毫秒");
//        System.out.println(resultList.size());
        es.close();
        
    }
}
