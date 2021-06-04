package spring.data;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.validation.DataBinder;
import java.beans.PropertyDescriptor;

/**
 * @author douzhitong
 * @date 2021/5/18
 */
public class DataBinderTest {

    public static void main(String[] args) {
//        bindTest();
//        getBeanInfo();
//        setValue();
    }

    public static void bindTest() {
        User user = new User();
        DataBinder dataBinder = new DataBinder(user, "user");
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("id", 10);
        propertyValues.addPropertyValue("name", " jerry");
        propertyValues.addPropertyValue("age", 18);
        dataBinder.bind(propertyValues);
        System.out.println(user);
    }

    public static void getBeanInfo() {
        BeanWrapper beanWrapper = new BeanWrapperImpl(User.class);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.printf("%s : %s, %s\n", propertyDescriptor.getName(),
                    propertyDescriptor.getReadMethod(),
                    propertyDescriptor.getWriteMethod());
        }
    }

    public static void setValue() {
        BeanWrapper beanWrapper = new BeanWrapperImpl(User.class);
        beanWrapper.setPropertyValue(new PropertyValue("id", 99));
        System.out.println(beanWrapper.getWrappedInstance());
    }


}
