package spring.data.convert;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.convert.TypeDescriptor;
import spring.data.User;

import java.util.List;

/**
 * @author douzhitong
 * @date 2021/5/19
 */
public class TypeConverterTest {

    public static void main(String[] args) {
//        convertToUser();
        convertToUserList();
    }

    public static void convertToUser() {
        SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();
        simpleTypeConverter.registerCustomEditor(User.class, new spring.data.convert.PropertyEditorTest.StringToUserPropertyEditor());
        User user = simpleTypeConverter.convertIfNecessary("1-jerry-8", User.class);
        System.out.println(user);
    }

    public static void convertToUserList() {
        SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();
        simpleTypeConverter.registerCustomEditor(User.class, new spring.data.convert.PropertyEditorTest.StringToUserPropertyEditor());
        List<User> user = simpleTypeConverter.convertIfNecessary("1-jerry-8,2-tony-10", List.class, TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(User.class)));
        System.out.println(user);
    }
}
