package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("constructor " + this.url);

    }

    public void setUrl(String url) {
        System.out.println("Set url = " + url);
        this.url = url;
    }

    public void connect() {
        System.out.println("Connect Start " + this.url);
    }

    public void call(String message) {
        System.out.println("message = " + message);
    }

    public void disconnect() {
        System.out.println("disconnect " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
