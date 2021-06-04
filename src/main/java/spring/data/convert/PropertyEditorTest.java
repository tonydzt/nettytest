package spring.data.convert;

import org.springframework.beans.PropertyAccessor;
import spring.data.User;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * @author douzhitong
 * @date 2021/5/19
 */
public class PropertyEditorTest {

    public static void main(String[] args) {
//        propertyEdit();
        List<String> strippedPaths = new ArrayList<>();
        addStrippedPropertyPaths(strippedPaths, "", "items[n].quantity[5].oom");
        System.out.println(strippedPaths);
    }

    public static void propertyEdit() {
        String text = "1-jerry-8";
        StringToUserPropertyEditor editor = new StringToUserPropertyEditor();
        editor.setAsText(text);
        User user = (User) editor.getValue();
        System.out.println(user);
    }

    public static void addStrippedPropertyPaths(List<String> strippedPaths, String nestedPath, String propertyPath) {
        int startIndex = propertyPath.indexOf(PropertyAccessor.PROPERTY_KEY_PREFIX_CHAR);
        if (startIndex != -1) {
            int endIndex = propertyPath.indexOf(PropertyAccessor.PROPERTY_KEY_SUFFIX_CHAR);
            if (endIndex != -1) {
                String prefix = propertyPath.substring(0, startIndex);
                String key = propertyPath.substring(startIndex, endIndex + 1);
                String suffix = propertyPath.substring(endIndex + 1, propertyPath.length());
                // Strip the first key.
                strippedPaths.add(nestedPath + prefix + suffix);
                // Search for further keys to strip, with the first key stripped.
                addStrippedPropertyPaths(strippedPaths, nestedPath + prefix, suffix);
                // Search for further keys to strip, with the first key not stripped.
                addStrippedPropertyPaths(strippedPaths, nestedPath + prefix + key, suffix);
            }
        }
    }

    public static class StringToUserPropertyEditor extends PropertyEditorSupport {
        // 例如: 1-jerry-8
        @Override
        public String getAsText() {
            User user = (User) getValue();
            return user.getId() + "-" + user.getName() + "-" + user.getAge();
        }
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            String[] strArray = text.split("-");
            User user = new User();
            user.setId(Integer.valueOf(strArray[0]));
            user.setName(strArray[1]);
            user.setAge(Integer.valueOf(strArray[2]));
            setValue(user);
        }
    }
}
