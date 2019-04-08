import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            foo();
            System.out.println("1");

        } catch (Exception e) {
            System.out.println("2");
        } finally {
            System.out.println("3");
        }
    }

    public static void foo() throws Exception {
        try {
            bar();
            System.out.println("4");
        } catch (IOException e) {
            System.out.println("5");
        } /*catch (Exception e) { // Dieses Exception ist war ursprünglich nicht vorhanden
            System.out.println("Warning soll doch noch hier gehandled werden" + e.getClass().getCanonicalName());
        }*/ finally {
            System.out.println("6");
        }
    }

    public static void bar() throws Exception {
        throw new Exception(); // oder hier kann man auch IOException eingeben
    }
}
