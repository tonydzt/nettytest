package guava.reflect;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author douzhitong
 * @date 2018/12/29
 */
public class TypeTokenTest {

    public static void main(String[] args) {
//        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
        TypeToken<Class<ArrayList>> typeToken = new TypeToken(ArrayList.class) {};
        System.out.println(getType(typeToken));
        System.out.println(getClass(typeToken));
        System.out.println(typeToken.getComponentType());
        System.out.println(typeToken.getTypes());
        System.out.println(typeToken.isPrimitive());
        System.out.println(typeToken.isArray());
        System.out.println(typeToken.getRawType().getTypeParameters().length);
        System.out.println(typeToken.resolveType(typeToken.getRawType().getTypeParameters()[0]));
        System.out.println(typeToken.method(typeToken.getRawType().getMethods()[0]));

    }

    private static Type getType(TypeToken typeToken) {
        return typeToken.getType();
    }

    private static Type getClass(TypeToken typeToken) {
        return typeToken.getRawType();
    }

}
