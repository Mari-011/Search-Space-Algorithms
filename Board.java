

import java.util.*;

public class Board {
    Cell[][] cells;
    int rows;
    int columns;
    Cell [][] copy;


    Cell[][] level4 = {
            {new Cell('D'), new Cell('L'), new Cell('D')},
            {new Cell('L'), new Cell('2'), new Cell('L')},
            {new Cell('D'), new Cell('L'), new Cell('D')}

    };
    Cell[][] level5 = {
            {new Cell('L'), new Cell('L'), new Cell('L')},
            {new Cell('D'), new Cell('3'), new Cell('D')},
            {new Cell('L'), new Cell('L'), new Cell('L')}

    };
    Cell[][] level6 = {
            {new Cell('0'), new Cell('D'), new Cell('0')},
            {new Cell('L'), new Cell('D'), new Cell('L')},

    };
    Cell[][] level7 = {
            {new Cell('D'), new Cell('3'), new Cell('D'), new Cell('D')},
            {new Cell('L'), new Cell('D'), new Cell('D'), new Cell('L')},
            {new Cell('D'), new Cell('D'), new Cell('D'), new Cell('2')}

    };
    Cell[][] level8 = {
            {new Cell('D'), new Cell('L'), new Cell('D')},
            {new Cell('D'), new Cell('3'), new Cell('D')},
            {new Cell('D'), new Cell('2'), new Cell('D')},
            {new Cell('D'), new Cell('L'), new Cell('D')}

    };
    Cell[][] level9 = {
            {new Cell('D'), new Cell('D'), new Cell('D'), new Cell('D'), new Cell('D')},
            {new Cell('L'), new Cell('2'), new Cell('D'), new Cell('3'), new Cell('D')},
            {new Cell('D'), new Cell('D'), new Cell('D'), new Cell('D'), new Cell('D')},
    };

    public Board(int numbord) {
        cells = init(numbord);
        // ... (existing constructor)
    }
public Board(Cell[][] cells){
        this.cells=cells;
}
    Cell[][] init(int numBoed) {
        if (numBoed == 4) {
            return level4;
        }
        if (numBoed == 5) {
            return level5;
        }
        if (numBoed == 6) {
            return level6;
        }
        if (numBoed == 7) {
            return level7;
        }
        if (numBoed == 8) {
            return level8;
        }
        if (numBoed == 9) {
            return level9;
        }

        return new Cell[0][];
    }


    public void printBoard() {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                System.out.print(cells[i][j].type + " | ");
            }
            System.out.println();
        }
    }


    public void changeStateOfCell(int indexI, int indexJ) {
        if (cells[indexI][indexJ].type == 'L') {
            cells[indexI][indexJ].type = 'D';
            int left = indexJ - 1;
            int right = indexJ + 1;
            int top = indexI - 1;
            int bottom = indexI + 1;
            if (left >= 0) {
                switch (cells[indexI][left].type) {
                    case 'L':
                        cells[indexI][left].type = 'D';
                        break;
                    case 'D':
                        cells[indexI][left].type = 'L';
                        break;
                }
            }
            if (right < cells[0].length) {
                switch (cells[indexI][right].type) {
                    case 'L':
                        cells[indexI][right].type = 'D';
                        break;
                    case 'D':
                        cells[indexI][right].type = 'L';
                        break;
                }
            }
            if (top >= 0) {
                switch (cells[top][indexJ].type) {
                    case 'L':
                        cells[top][indexJ].type = 'D';
                        break;
                    case 'D':
                        cells[top][indexJ].type = 'L';
                        break;


                }

            }
            if (bottom < cells.length) {
                switch (cells[bottom][indexJ].type) {
                    case 'L':
                        cells[bottom][indexJ].type = 'D';
                        break;
                    case 'D':
                        cells[bottom][indexJ].type = 'L';
                        break;


                }
            }
        } else {
            System.out.print("Enter Only Cell Light");
            System.out.println();

        }


    }

    public boolean checkWin() {
        boolean flag = true;
        for (int i = 0; i < cells.length; i++) {

            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].type != 'L' && cells[i][j].type != 'D') {
                    int sumLights = 0;
                    int cellNumber = Character.getNumericValue(cells[i][j].type);
                    int left = j - 1;
                    int right = j + 1;
                    int top = i - 1;
                    int bottom = i + 1;
                    if (left >= 0) {
                        if (cells[i][left].type == 'L') {
                            sumLights = sumLights + 1;
                        }
                    }
                    if (right < cells[0].length) {
                        if (cells[i][right].type == 'L') {
                            sumLights = sumLights + 1;
                        }
                    }
                    if (top >= 0) {
                        if (cells[top][j].type == 'L') {
                            sumLights = sumLights + 1;
                        }

                    }
                    if (bottom < cells.length) {
                        if (cells[bottom][j].type == 'L') {
                            sumLights = sumLights + 1;
                        }
                    }
                    if (flag==true && sumLights == cellNumber) {
                        flag = true;
                    } else
                        flag = false;//number cells
sumLights=0;

                }
            }



        }
        return flag;

    }


//
    public Cell[][] copyCell(Cell[][] cells){
        Cell[][] copy=new Cell[cells.length][cells[0].length];
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[0].length;j++){
                copy[i][j]=new Cell(cells[i][j].type);
            }
        }
        return cells;
    }
//
    public void  printAllPossibleStates(){
        Cell[][] originalState=copyCell(cells);
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[0].length;j++){
                if(cells[i][j].type=='L'){
                    changeStateOfCell(i,j);
                    System.out.println();
                    printBoard();
                   cells= originalState;
                    System.out.println();
                }
            }
        }
    }



public ArrayList<Board> getAllPossibleState(){
ArrayList<Board> get=new ArrayList();
    Cell[][] originalState=copyCell(cells);
    for(int i=0;i<cells.length;i++){
        for(int j=0;j<cells[0].length;j++){
            if(cells[i][j].type=='L'){
                changeStateOfCell(i,j);
                get.add(new Board(cells));
                cells= originalState;

            }
        }

    }
    return get;
}
    public Board  DFS(){
       Cell [][] copy= copyCell(cells);

       Stack<Board> stack= new Stack();
       ArrayList<Board> visited=new ArrayList();
       stack.push(new Board(cells));
       while(!stack.isEmpty()){
           Board  currentNode=stack.pop();
//         isVisited(currentNode);

           visited.add(currentNode);

         if(currentNode.checkWin()){
             System.out.println("llllllllllll");
            return  currentNode;
         }
         if(!checkWin()){
             ArrayList<Board> allPossible=getAllPossibleState();
             for (int i=0;i<allPossible.size();i++){
                 if(!visited.contains(allPossible.get(i))){
//                     visited.add(allPossible.get(i));
                     stack.push(allPossible.get(i));

                 }

             }
         }



       }
        return new Board(copy);


}

    public Board  BFS(){
        Cell [][] copy= copyCell(cells);

        Queue<Board> queue= new LinkedList<>();
        ArrayList<Board> visited=new ArrayList();
        queue.add(new Board(cells));
        while(!queue.isEmpty()){
            Board  currentNode=queue.poll();
//         isVisited(currentNode);

            visited.add(currentNode);

            if(currentNode.checkWin()){
                System.out.println("llllllllllll");
                return  currentNode;
            }
            if(!checkWin()){
                ArrayList<Board> allPossible=getAllPossibleState();
                for (int i=0;i<allPossible.size();i++){
                    if(!visited.contains(allPossible.get(i))){
//                     visited.add(allPossible.get(i));
                        queue.add(allPossible.get(i));

                    }

                }
            }



        }
        return new Board(copy);


    }

}