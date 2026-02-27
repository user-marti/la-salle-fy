import java.util.Scanner;

public class MenuController implements Menu {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void show(String s) {
        System.out.println(s);
    }

    @Override
    public Integer getInteger() {
        try {
            String input = sc.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; 
        }
    }

    @Override
    public String getString() {
        return sc.nextLine();
    }

    public void showMainMenu() {
        show("\nLasallefy");
        show("1. Gestionar cançons");
        show("2. Gestionar playlists");
        show("3. Reproduir");
        show("Q. Sortir");
        System.out.print("Tria una opció: ");
    }
}