package guava.reflect;

import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author douzhitong
 * @date 2018/12/29
 */
public class ProxyTest {

    public static void main(String[] args) {
        Person person = Reflection.newProxy(Person.class, new MyInvocationHandler(new PersonImpl()));
        person.getName("test");

    }

    static class MyInvocationHandler implements InvocationHandler {

        private Object obj;

        MyInvocationHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("start proxy");
            Object result = method.invoke(obj, args);
            System.out.println("end proxy");
            return result;
        }
    }

    interface Person {
        void getName(String prefix);
    }

    static class PersonImpl implements Person {
        @Override
        public void getName(String prefix) {
            System.out.println(prefix + " tony");
        }

        public String getHa() {
            return " hahaha";
        }
    }
}
