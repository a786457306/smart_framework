package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 代理实例的关联的调用程序需要实现InvocationHandler接口
// invoke方法在实例上处理方法的调用，并返回结果
public class DynamicProxy implements InvocationHandler {

    // 被代理的目标对象，通过构造方法进行初始化（注入）
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    // 避免到处都是Proxy.newProxyInstance
    // 忽略编译时的警告，因为强转类型时向下转型了
    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    // 通过反射调用方法，调用前后进行处理，最后返回result
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("after");
    }

    private void before() {
        System.out.println("before");
    }
}
