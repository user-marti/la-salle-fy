import java.util.Scanner;

public class MenuController implements Menu{
    Scanner sc = new Scanner(System.in);

    @Override
    public void show(String s) {
        System.out.println(s);
    }

    @Override
    public Integer getInteger() {
        return sc.nextInt();
    }

    @Override
    public String getString() {
        return sc.nextLine();
    }
}
