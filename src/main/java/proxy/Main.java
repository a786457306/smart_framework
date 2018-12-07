package proxy;

public class Main {
    public static void main(String[] args) {

        DynamicProxy proxy = new DynamicProxy(new HelloImpl());
        Hello helloProxy = proxy.getProxy();

        helloProxy.say("Jack");
    }
}
