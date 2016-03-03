package forum.web.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.web.dao.pojo.User;
import forum.web.service.UsersService;

@Controller
public class UsersController{
	
	@Autowired
	private UsersService usersService;

	@RequestMapping("/hello")
	public String haha(HttpServletRequest request,Model model) {
//		User user = new User();
//		model.addAttribute("message", "你好呀");
		List<User> list = new ArrayList<User>();
//		user.setUsername("yxm");
//		user.setPassword("123");
//		user.setIfDelete(0);
//		user.setIfLogin(0);
//		user.setCreateTime(new Date());
//		user.setModifyTime(new Date());
//		usersService.addUsers(user);
		list = usersService.listAll();
		
		return "login";
	}
	
	public static void main(String[] args) {
	    
	    String json = "{" +
	            "\"user\":\"kimchy\"," +
	            "\"postDate\":\"2013-01-30\"," +
	            "\"message\":\"trying out Elasticsearch\"" +
	        "}";
	    
	    try {
            Client client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
            IndexResponse response = client.prepareIndex("twitter", "tweet")
                    .setId("2")
                    .setSource(json)
                    .get();
            
            
//            DeleteResponse response1 = client.prepareDelete("twitter", "tweet", "AVMbZ_YZmFpB6y-Ugamg").get();

//            SearchResponse response1 = client.prepareSearch("twitter")
//                    .setTypes("tweet")
//                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                    .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
////                    .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
//                    .setFrom(0).setSize(60).setExplain(true)
//                    .execute()
//                    .actionGet();
            
            System.out.println(response.isCreated());
            client.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
