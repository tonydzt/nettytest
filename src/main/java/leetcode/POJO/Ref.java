package leetcode.POJO;

/**
 * 用来包装引用，解决Java无法传递引用的问题
 *
 * @author douzhitong
 * @date 2020/12/3
 */
public class Ref<T> {

    private T object;

    public Ref(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }

    public void set(T t) {
        this.object = t;
    }
}
