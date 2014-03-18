package guru.nidi.j8unit;

import org.junit.runner.Description;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class J8UnitRunner extends BlockJUnit4ClassRunner {
    public J8UnitRunner(Class<?> klass) throws InitializationError {
        super(init(klass));
    }

    private static Class<?> init(Class<?> klass) {
        try {
            klass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return klass;
    }

    @Override
    protected String testName(FrameworkMethod method) {
        if (method instanceof RunnableFrameworkMethod) {
            return J8unit.description(((RunnableFrameworkMethod) method).getRunnable());
        }
        return super.testName(method);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        return J8unit.methods(getTestClass().getName()).stream().map(tm->new RunnableFrameworkMethod(tm.getRunnable())).collect(Collectors.toList());
    }


}
