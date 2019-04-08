import java.util.Map;

public class play {

    public static void main(String[] args) {
        try {
            System.out.println("1");
            foo();
            System.out.println("2");
        } catch (Exception e) {
            System.out.println("3");
        } finally {
            System.out.println("4");
        }
        System.out.println("5");
    }

    public static void foo() throws Exception {
        try {
            System.out.println("6");
            if (true) throw new Exception();
            System.out.println("7");
        } catch (Exception e) {
            System.out.println("8");
        } finally {
            System.out.println("9");
        }
        System.out.println("10");


        //System.out.println(10/0);
    }
}
