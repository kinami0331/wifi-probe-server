package cc.kinami.wp.websocket;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ServerEndpoint(value = "/websocket/mac-detect/{mac}")
public class MacDetectWebSocket {

    private static final CopyOnWriteArrayList<MacDetectWebSocket> webSocketSet = new CopyOnWriteArrayList<>();

    private Session session;

    private String mac;

    public static void sendAllMessage(String message) {
        for (MacDetectWebSocket webSocket : webSocketSet) {
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("mac")String mac) {
        this.session = session;
        System.out.println(session.getPathParameters());
        webSocketSet.add(this);
        this.mac = mac;
        System.out.println("有一个连接，目标mac地址为: " + mac);
        System.out.println(session.getRequestURI());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}
