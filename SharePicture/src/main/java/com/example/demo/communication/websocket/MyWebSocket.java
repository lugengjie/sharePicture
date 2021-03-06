package com.example.demo.communication.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.communication.entity.PrivateLetterDTO;
import com.google.gson.Gson;
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)
@Component
public class MyWebSocket {

	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private Long id;
    
    
	/**
      * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
    	HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
    	Long userId = (Long)httpSession.getAttribute("userId");
        this.session = session;
        this.id=userId;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();
        //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	System.out.println("来自客户端的消息:" + message);
    	PrivateLetterDTO msg = new Gson().fromJson(message,PrivateLetterDTO.class);
    	sendOne(message,msg.getReceiverId());
        
 	   
    }
    
    /*
     * 群发消息
     */
    public void sendAll(String message) {
    	for (MyWebSocket item : webSocketSet) {
        	synchronized (item){
	            try {
	            	if(!item.equals(this))
	                  item.sendMessage(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
        	}
        }
	}
    
    /*
     * 定向发送消息
     */
    public void sendOne(String message,Long id) {
    	if(message!=null&&id!=null) {	
    		for(MyWebSocket item:webSocketSet) {
    			if(item.id.equals(id)) {
    				synchronized (item){
    		            try {
    		                item.sendMessage(message);
    		            } catch (IOException e) {
    		                e.printStackTrace();
    		            }
    	        	}
    				break;
    			}
    		}
    	}
	
	}
    
    /*
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
