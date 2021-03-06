package ConnectFour;

import java.util.ArrayList;

/** Class that represents the game grid.
 * @author Duncan Mac Leod
 */
public class Grid {
    Piece[][] board;
    int[][] con4;
    boolean won;

    public Grid () {
        board = new Piece[6][7];
        con4 = new int[5][2];
        won = false;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    /**
     * @return height of the board
     */
    public int getHeight() {
        return board.length;
    }

    /**
     * @return width of the board
     */
    public int getWidth() {
        return board[0].length;
    }

    /**
     * Adds PIECE to the grid at the desired COLUMN.
     */
    public void addPiece (int column, Piece piece) {
        if (column >= getWidth() || column < 0) {
            throw new IndexOutOfBoundsException("Select a proper column index.");
        }
        for (int i = getHeight() - 1; i >= 0; i--) {
            if (board[i][column].equals(Piece.EMPTY)) {
                board[i][column] = piece;
                break;
            }
        }
    }

    public boolean isStaleMate () {
        for (int i = 0; i < getWidth(); i++) {
            if (board[0][i].equals(Piece.EMPTY)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLegal (int column) {
        if (column >= getWidth() || column < 0) {
            throw new IndexOutOfBoundsException("Select a proper column index.");
        }
        return board[0][column].equals(Piece.EMPTY);
    }

    public ArrayList<Integer> legalMoves () {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int i = 0; i < getWidth(); i++) {
            if (isLegal(i)) {
                moves.add(i);
            }
        }
        return moves;
    }

    public boolean wonGame (Piece turn) {

        // horizontalCheck
        for (int j = 0; j < getWidth() - 3; j++) {
            for (int i = 0; i < getHeight(); i++){
                if (this.board[i][j] == turn && this.board[i][j+1] == turn && this.board[i][j+2] == turn && this.board[i][j+3] == turn){
                    con4[0][0] = i;
                    con4[0][1] = j;

                    con4[1][0] = i;
                    con4[1][1] = j+1;

                    con4[2][0] = i;
                    con4[2][1] = j+2;

                    con4[3][0] = i;
                    con4[3][1] = j+3;

                    con4[4][0] = 0;

                    won = true;
                    return true;
                }
            }
        }
        // verticalCheck
        for (int i = 0; i < this.getHeight() - 3; i++){
            for (int j = 0; j < getWidth(); j++){
                if (this.board[i][j] == turn && this.board[i+1][j] == turn && this.board[i+2][j] == turn && this.board[i+3][j] == turn){
                    con4[0][0] = i;
                    con4[0][1] = j;

                    con4[1][0] = i+1;
                    con4[1][1] = j;

                    con4[2][0] = i+2;
                    con4[2][1] = j;

                    con4[3][0] = i+3;
                    con4[3][1] = j;

                    con4[4][0] = 1;

                    won = true;
                    return true;
                }
            }
        }
        // descendingDiagonalCheck
        for (int j = 0; j < getWidth()-3; j++){
            for (int i = 0; i < getHeight()-3; i++){
                if (this.board[i][j] == turn && this.board[i+1][j+1] == turn && this.board[i+2][j+2] == turn && this.board[i+3][j+3] == turn) {
                    con4[0][0] = i;
                    con4[0][1] = j;

                    con4[1][0] = i+1;
                    con4[1][1] = j+1;

                    con4[2][0] = i+2;
                    con4[2][1] = j+2;

                    con4[3][0] = i+3;
                    con4[3][1] = j+3;

                    con4[4][0] = 2;

                    won = true;
                    return true;
                }
            }
        }
        // ascendingDiagonalCheck
        for (int j = 0; j < getWidth()-3; j++){
            for (int i = getHeight() - 1; i > 2; i--){
                if (this.board[i][j] == turn && this.board[i-1][j+1] == turn && this.board[i-2][j+2] == turn && this.board[i-3][j+3] == turn) {
                    con4[0][0] = i;
                    con4[0][1] = j;

                    con4[1][0] = i-1;
                    con4[1][1] = j+1;

                    con4[2][0] = i-2;
                    con4[2][1] = j+2;

                    con4[3][0] = i-3;
                    con4[3][1] = j+3;

                    con4[4][0] = 3;

                    won = true;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String grid = "  0 1 2 3 4 5 6  \n";
        grid += "  _ _ _ _ _ _ _\n";
        for (int i = 0; i < getHeight(); i++) {
            grid += "| ";
            for (Piece piece : board[i]) {
                grid += Piece.rep(piece);
                grid += " ";
            }
            grid += "|";
            grid += "\n";
        }
        grid += "| - - - - - - - |\n";
        grid += "|               |\n";
        grid += "|               |\n";
        grid += "--             --\n";
        return grid;
    }
}
