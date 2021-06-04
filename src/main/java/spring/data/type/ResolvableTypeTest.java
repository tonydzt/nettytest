package spring.data.type;

import org.springframework.core.ResolvableType;
import spring.data.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author douzhitong
 * @date 2021/5/21
 */
public class ResolvableTypeTest {

    public static void main(String[] args) {
//        resolveListClass();
        resolveArrClass();
//        resolveMapClass();
    }

    public static void resolveListClass() {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(List.class, User.class);
        System.out.println("Component type is: " + resolvableType.getComponentType());
        System.out.println("Raw class is: " + resolvableType.getRawClass());
        System.out.println("Source is: " + resolvableType.getSource());
        System.out.println("Super type is: " + resolvableType.getSuperType());
        System.out.println("Type is: " + resolvableType.getType());
        System.out.println("Nested 1 Type is: " + resolvableType.getNested(1).getType());
        System.out.println("Generics is: " + Arrays.toString(resolvableType.getGenerics()));
        System.out.println("Interfaces is: " + Arrays.toString(resolvableType.getInterfaces()));
        System.out.println("Generic 1 is: " + resolvableType.getGeneric(0));
    }

    public static void resolveArrClass() {
        ResolvableType resolvableType = ResolvableType.forClass(User[].class);
        System.out.println("Component type is: " + resolvableType.getComponentType());
        System.out.println("Raw class is: " + resolvableType.getRawClass());
        System.out.println("Source is: " + resolvableType.getSource());
        System.out.println("Super type is: " + resolvableType.getSuperType());
        System.out.println("Type is: " + resolvableType.getType());
        System.out.println("Nested 1 Type is: " + resolvableType.getNested(1).getType());
        System.out.println("Nested 2 Type is: " + resolvableType.getNested(2).getType());
        System.out.println("Nested 3 Type is: " + resolvableType.getNested(3).getType());
        System.out.println("Generics is: " + Arrays.toString(resolvableType.getGenerics()));
        System.out.println("Interfaces is: " + Arrays.toString(resolvableType.getInterfaces()));
        System.out.println("Generic 1 is: " + resolvableType.getGeneric(0));
    }

    public static void resolveMapClass() {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(Map.class, Integer.class, User.class);
        System.out.println("Component type is: " + resolvableType.getComponentType());
        System.out.println("Raw class is: " + resolvableType.getRawClass());
        System.out.println("Source is: " + resolvableType.getSource());
        System.out.println("Super type is: " + resolvableType.getSuperType());
        System.out.println("Type is: " + resolvableType.getType());
        System.out.println("Nested 0 Type is: " + resolvableType.getNested(0).getType());
        System.out.println("Nested 1 Type is: " + resolvableType.getNested(1).getType());
        System.out.println("Nested 2 Type is: " + resolvableType.getNested(2).getType());
        System.out.println("Nested 3 Type is: " + resolvableType.getNested(3).getType());
        System.out.println("Nested 4 Type is: " + resolvableType.getNested(4).getType());
        System.out.println("Generics is: " + Arrays.toString(resolvableType.getGenerics()));
        System.out.println("Interfaces is: " + Arrays.toString(resolvableType.getInterfaces()));
        System.out.println("Generic 0 is: " + resolvableType.getGeneric(0));
        System.out.println("Generic 1 is: " + resolvableType.getGeneric(1));
    }
}
