import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Life.
 */
public class Life {
    /**
     * The Maxrow.
     */
// constants
    final int MAXROW = 20;
    /**
     * The Maxcol.
     */
    final int MAXCOL = 60;

    /**
     * The Grid.
     */
    int[][] grid = new int[MAXROW + 2][MAXCOL + 2];


    /**
     * Intialize.
     *
     * @throws IOException the io exception
     */
    public void initialize() throws IOException {
        // declaring variables
        int row, col;

        // creating the stream
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int rowIndex = 0; rowIndex < MAXROW + 1; rowIndex++){
            for(int colIndex = 0; colIndex < MAXCOL; colIndex++){
                grid[rowIndex][colIndex] = 0;
            }
        }

        System.out.println("List the coordinates of the living cells.");
        System.out.println("Terminate the list with the special pair -1 -1");
        System.out.print("Please enter the coordinates like 1 2 :");
        String val = br.readLine();
        try{
            row = Integer.parseInt(val.split(" ",2)[0]);
            col = Integer.parseInt(val.split(" ",2)[1]);

            while(row != -1 || col != -1){
                if(row > 1 && row < MAXROW){
                    if(col > 1 && col < MAXCOL){
                        grid[row][col] = 1;
                    }else{
                        System.out.println("Column " + col + " is out of range");
                    }
                }else{
                    System.out.println("Row " + row + " is out of range");
                }

                System.out.println("Terminate the list with the special pair -1 -1");
                System.out.print("Please enter the coordinates like 1 2 :");
                String newVal = br.readLine();
                row = Integer.parseInt(newVal.split(" ",2)[0]);
                col = Integer.parseInt(newVal.split(" ",2)[1]);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }



    }

    /**
     * Print.
     */
    public void print() {
        System.out.println("The current Life Configuration");
        for(int rowIndex = 1; rowIndex < MAXROW; rowIndex++){
            for(int colIndex = 1; colIndex < MAXCOL; colIndex++){
                if(grid[rowIndex][colIndex] == 1){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Update.
     */
    public void update() {
        int[][] newGrid = new int[MAXROW + 2][MAXCOL + 2];
        for(int rowIndex = 1; rowIndex < MAXROW; rowIndex++){
            for(int colIndex = 1; colIndex < MAXCOL; colIndex++){
                switch(neighborCount(rowIndex,colIndex)){
                    case 2:
                        newGrid[rowIndex][colIndex] = grid[rowIndex][colIndex];     // Status stays the same
                        break;
                    case 3:
                        newGrid[rowIndex][colIndex] = 1;    // Cell is now alive
                        break;
                    default:
                        newGrid[rowIndex][colIndex] = 0;    // Cell is now dead
                }
            }
        }

        for(int rowIndex = 1; rowIndex < MAXCOL; rowIndex++){
            for(int colIndex = 1; colIndex < MAXCOL; colIndex++){
                grid[rowIndex][colIndex] = newGrid[rowIndex][colIndex];
            }
        }
    }


    private int neighborCount(int row, int col) {
        int count = 0;
        for(int rowIndex = row - 1; rowIndex <= row + 1; rowIndex++){
            for(int colIndex = col - 1; colIndex <= col + 1; colIndex++){
                count += grid[rowIndex][colIndex];          // increase count is neighbour is alive
            }
        }

        // reduce count if cell is not its own neighbour
        count -= grid[row][col];

        return count;
    }
}
