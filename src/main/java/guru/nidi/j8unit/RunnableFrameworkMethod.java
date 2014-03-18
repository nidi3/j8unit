package guru.nidi.j8unit;

import org.junit.runners.model.FrameworkMethod;

import java.lang.reflect.Method;

/**
 *
 */
public class RunnableFrameworkMethod extends FrameworkMethod {
    private final Runnable runnable;

    public RunnableFrameworkMethod(Runnable runnable) {
        super(runMethod(runnable));
        getMethod().setAccessible(true);
        this.runnable=runnable;
    }

    private static Method runMethod(Runnable r) {
        try {
            return r.getClass().getMethod("run");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object invokeExplosively(Object target, Object... params) throws Throwable {
        return super.invokeExplosively(runnable, params);
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
