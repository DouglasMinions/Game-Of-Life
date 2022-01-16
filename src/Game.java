import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Game.
 */
public class Game {
    /**
     * The Maxrow.
     */
    static final int MAXROW = 20;
    /**
     * The Maxcol.
     */
    static final int MAXCOL = 60;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        // declaring variable
        char status;


        // creating object
        Life obj = new Life();


        // creating stream
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        instructions();
        obj.initialize();
        obj.print();
        System.out.print("Continue viewing new generations? [y/n]");
        status = br.readLine().charAt(0);
        while (user_says_yes(status)) {
            obj.update();
            obj.print();
            System.out.print("Continue viewing new generations? [y/n]");
            status = br.readLine().charAt(0);
        }
    }

    /**
     * Instructions.
     */
    public static void instructions() {
        System.out.println("Welcome to Conway's Game of Life");
        System.out.println("This game uses a grid size of " + MAXROW + " by " + MAXCOL + " in which\n");
        System.out.println("each cell can either be occupied by an organism or not");
        System.out.println("The occupied cells change from generation to generation");
        System.out.println("according to the number of neighbouring cells which are alive");
    }

    /**
     * User says yes boolean.
     *
     * @param status the status
     * @return the boolean
     */
    public static boolean user_says_yes(char status) {
        return status == 'y' || status == 'Y';
    }

}
