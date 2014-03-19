package guru.nidi.j8unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class J8Unit {
    private static HashMap<String, List<TestMethod>> methods = new HashMap<>();
    private static ThreadLocal<TestMethod> current = new ThreadLocal<>();

    private J8Unit() {
    }

    public static void describe(String description, Runnable test) {
        TestMethod old = current.get();
        current.set(register(description, test));
        test.run();
        current.set(old);
    }

    public static void it(String description, Runnable test) {
        register(description, test);
    }

    private static TestMethod register(String description, Runnable test) {
        String cName = test.getClass().getCanonicalName();
        int pos = cName.indexOf("$$");
        String testClass = cName.substring(0, pos);
        List<TestMethod> tests = methods.get(testClass);
        if (tests == null) {
            tests = new ArrayList<>();
            methods.put(testClass, tests);
        }
        TestMethod tm = new TestMethod(current.get(), description, test);
        tests.add(tm);
        return tm;
    }

    public static List<TestMethod> methods(String className) {
        return methods.get(className);
    }

    public static String description(Runnable runnable) {
//        for (List<TestMethod> ms : methods.values()) {
//            for (TestMethod m : ms) {
//                if (m.getRunnable() == runnable) {
//                    return m.getDescription();
//                }
//            }
//        }
//        return "unknown";
        return methods.values().stream()
                .flatMap(tms -> tms.stream().filter(tm -> tm.getRunnable() == runnable))
                .findFirst()
                .map(TestMethod::getDescription)
                .orElse("unknown");
    }

    private static class TestMethod {
        private final TestMethod parent;
        private final String description;
        private final Runnable runnable;

        public TestMethod(TestMethod parent, String description, Runnable runnable) {
            this.parent = parent;
            this.description = description;
            this.runnable = runnable;
        }

        public String getDescription() {
            String res = "";
            TestMethod tm = this;
            while (tm != null) {
                res = tm.description + " " + res;
                tm = tm.parent;
            }
            return res;
        }

        public Runnable getRunnable() {
            return runnable;
        }


    }
}




