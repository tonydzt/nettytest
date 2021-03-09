package mockito;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class MyProxyMockito {

    public static final Map<ProxyMethodInfo, Object> MOCKED_METHODS = new HashMap<>();

    private static ProxyMockInjector when(Object methodCall) {
        return new ProxyMockInjector((ProxyMethodInfo) methodCall);
    }

    private static MyProxy getProxy(Object object) {
        return new MyProxy(object);
    }

    private static Object mock(final Class<?> t) {
        try {
            return getProxy(t.newInstance()).getInstance(t);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        final List myMockList1 = (List) mock(ArrayList.class);
        final List myMockList2 = (List) mock(ArrayList.class);
        final Map myMockMap = (Map) mock(HashMap.class);

        MyProxyMockito.when(myMockList1.get(0)).thenReturn("Hello, I am James");
        MyProxyMockito.when(myMockList1.get(2)).thenReturn("Hello, I am Billy");
        MyProxyMockito.when(myMockList2.get(0)).thenReturn("Hello, I am Tom");
        MyProxyMockito.when(myMockMap.get(10)).thenReturn("Hello, I am Bob");

        System.out.println("myMockList1.get(0) = " + myMockList1.get(0));
        System.out.println("myMockList1.get(2) = " + myMockList1.get(2));
        System.out.println("myMockList2.get(0) = " + myMockList2.get(0));
        System.out.println("myMockMap.get(10) = " + myMockMap.get(10));
    }
}

class ProxyMockInjector {

    private final ProxyMethodInfo methodInfo;

    ProxyMockInjector(ProxyMethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public void thenReturn(final Object mockResult) {
        MyProxyMockito.MOCKED_METHODS.put(methodInfo, mockResult);
    }
}

class ProxyMethodInfo {

    private final InvocationHandler invocationHandler;
    private final Method method;
    private final Object[] args;

    ProxyMethodInfo(InvocationHandler invocationHandler, Method method, Object[] args) {
        this.invocationHandler = invocationHandler;
        this.method = method;
        this.args = args;
    }

    @Override
    public String toString() {
        return "{invocationHandler: " + invocationHandler + ", Method: " + method + ", args: " + Arrays.toString(args) + "}";
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof ProxyMethodInfo) {
            final ProxyMethodInfo otherMethodInfo = (ProxyMethodInfo) other;
            return invocationHandler.equals(otherMethodInfo.invocationHandler) && method.equals(otherMethodInfo.method) && Arrays.equals(args, otherMethodInfo.args);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return invocationHandler.hashCode() + method.hashCode() + Arrays.hashCode(args);
    }
}

class MyProxy implements InvocationHandler {

    private Object object;

    MyProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if ("get".equals(method.getName())) {
            final ProxyMethodInfo key = new ProxyMethodInfo(this, method, args);
            final boolean hasMocked = MyProxyMockito.MOCKED_METHODS.containsKey(key);
            if (!hasMocked) {
                System.out.println("Initializing the mock for " + key.toString());
                return key;
            } else {
                System.out.println("Returns the mock result:");
                return MyProxyMockito.MOCKED_METHODS.get(key);
            }
        }

        return method.invoke(object, args);
    }

    public <T> T getInstance(Class<T> t) {

        return (T)Proxy.newProxyInstance(t.getClassLoader(), t.getInterfaces(), this);
    }
}

 
 