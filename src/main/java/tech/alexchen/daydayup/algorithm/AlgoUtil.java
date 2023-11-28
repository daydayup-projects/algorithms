package tech.alexchen.daydayup.algorithm;

/**
 * @author alexchen
 */
public class AlgoUtil {

    private static ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

    public static void printIndent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("  ");
        }
    }

    public static void printIndent() {
        printIndent(count.get());
    }

    public static void printIndentBefore() {
        printIndent();
        before();
    }

    public static void printIndentAfter() {
        printIndent();
        after();
    }

    public static void before() {
        count.set(count.get() + 1);
    }

    public static void after() {
        count.set(count.get() - 1);
    }
}
