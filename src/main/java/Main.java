import java.util.List;

public class Main {

    private static final int MY_INT = 7; // ok, field declaration

    public static void main(String[] args) {
        var test = List.of("test", "test3", "test5");
        if (test.size() > 2) {
            System.out.println("test");
        } else {
            System.out.println("test2");
        }
        System.out.println(test.get(2));

    }
}

