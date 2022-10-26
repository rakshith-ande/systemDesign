import java.util.*;

class Cells{
   
    private int width;
    private int height;
    private boolean[][] cells;

    public Cells(int width, int height){
        this.width = width;
        this.height = height;
        cells = new boolean[width][height];

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                cells[i][j] = false;
            }
        }
    }

    public void setInitialLiveCell(int x_position, int y_position, boolean value){
        cells[x_position][y_position] = value; 
    }


    public int getLiveNeighboursCount(int x_position, int y_position){ 
        int count = 0;   
        //System.out.println("x_position, y_position "+ x_position+"  "+y_position);

        for(int i=x_position-1;i<=x_position+1;i++){
            for(int j=y_position-1;j<=y_position+1;j++){
                try{
                    if(cells[i][j]){
                        //System.out.println("ERROR   "+ i +", "+j);
                        count++;
                    }    
                }catch(Exception e){

                }
            }
        }
        //System.out.println("Count Value "+ count);
        return count;
    }

    public void nextGen(){
        System.out.println();System.out.println("INPUT:::::");
        for(int i=0;i<this.width;i++){
            for(int j=0;j<this.height;j++){
                System.out.print(cells[i][j]+"    ");
            }
            System.out.println();
        }


        for(int i=0;i<this.width;i++){
            for(int j=0;j<this.height;j++){
                int liveNeighboursCount = getLiveNeighboursCount(i, j);

                if(cells[i][j]){
                    //if cell is live make the necessary actoins
                    if(liveNeighboursCount < 2){
                        cells[i][j] = false;
                    }
                    if(liveNeighboursCount > 3){
                        cells[i][j] = false;
                    }
                    if(liveNeighboursCount == 2 || liveNeighboursCount == 3){
                        cells[i][j] = true;
                    }
                }
                else{
                    //if cell is dead make the necessary actoins
                    if(liveNeighboursCount == 3){
                        cells[i][j] = true;
                    }
                }
            }

        }

        System.out.println();System.out.println("OUTPUT:::::");
        for(int i=0;i<this.width;i++){
            for(int j=0;j<this.height;j++){
                System.out.print(cells[i][j]+"    ");
            }
            System.out.println();
        }
    }
}


class Grid{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter width and Height");
        int width = scan.nextInt();
        int height = scan.nextInt();

        Cells cells = new Cells(width, height);
        
        System.out.println("Enter how many cells are live by default");
        int nCellLiveDefault = scan.nextInt();
        while(nCellLiveDefault-- >0){
            System.out.println("Enter live Cells coordinates");
            int x = scan.nextInt();
            int y = scan.nextInt();

            cells.setInitialLiveCell(x, y, true);
        }

        while(true){cells.nextGen();}
    }
}