package mockito;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMockito {

    public static final Map<MethodInfo, Object> MOCKED_METHODS = new HashMap<>();

    private static MockInjector when(Object methodCall) {
        return new MockInjector((MethodInfo) methodCall);
    }

    private static MyCGLibInterceptor getInterceptor() {
        return new MyCGLibInterceptor();
    }

    private static Object mock(final Class<?> t) {
        return getInterceptor().getInstance(t);
    }

    public static void main(String[] args) {
        final List<String> myMockList1 = (List<String>) mock(List.class);
        final List<String> myMockList2 = (List<String>) mock(List.class);
        final Map<Integer, String> myMockMap = (Map<Integer, String>) mock(Map.class);

        MyMockito.when(myMockList1.get(0)).thenReturn("Hello, I am James");
        MyMockito.when(myMockList1.get(2)).thenReturn("Hello, I am Billy");
        MyMockito.when(myMockList2.get(0)).thenReturn("Hello, I am Tom");
        MyMockito.when(myMockMap.get(10)).thenReturn("Hello, I am Bob");

        System.out.println("myMockList1.get(0) = " + myMockList1.get(0));
        System.out.println("myMockList1.get(2) = " + myMockList1.get(2));
        System.out.println("myMockList2.get(0) = " + myMockList2.get(0));
        System.out.println("myMockMap.get(10) = " + myMockMap.get(10));
    }
}

class MethodInfo {

    private final MyCGLibInterceptor interceptor;
    private final Method method;
    private final Object[] args;

    MethodInfo(MyCGLibInterceptor interceptor, Method method, Object[] args) {
        this.interceptor = interceptor;
        this.method = method;
        this.args = args;
    }

    @Override
    public String toString() {
        return "{interceptor: " + interceptor + ", Method: " + method + ", args: " + Arrays.toString(args) + "}";
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof MethodInfo) {
            final MethodInfo otherMethodInfo = (MethodInfo) other;
            return interceptor.equals(otherMethodInfo.interceptor) && method.equals(otherMethodInfo.method) && Arrays.equals(args, otherMethodInfo.args);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return interceptor.hashCode() + method.hashCode() + Arrays.hashCode(args);
    }
}

class MockInjector {

    private final MethodInfo methodInfo;

    MockInjector(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public void thenReturn(final Object mockResult) {
        MyMockito.MOCKED_METHODS.put(methodInfo, mockResult);
    }
}

class MyCGLibInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy) throws Throwable {
        final MethodInfo key = new MethodInfo(this, method, args);
        final boolean hasMocked = MyMockito.MOCKED_METHODS.containsKey(key);
        if (!hasMocked) {
            System.out.println("Initializing the mock for " + key.toString());
            return key;
        } else {
            System.out.println("Returns the mock result:");
            return MyMockito.MOCKED_METHODS.get(key);
        }
    }

    public Object getInstance(final Class<?> t) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}


 
 