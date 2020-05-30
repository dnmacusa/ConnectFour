package ConnectFour;

public class Game {

    Piece turn;
    Grid grid;
    ConnectAgent agent;

    public Game () {
        turn = Piece.RED;
        grid = new Grid();
        agent = new ConnectAgent();
    }

    public void add(int column) {
        grid.addPiece(column, turn);
    }

    public void updateTurn() {
        turn = Piece.opposite(turn);
    }

    public boolean won() {
        return grid.wonGame(turn);
    }

    public boolean play(String AI, int column) {
        if (AI != null) {
            add(column);
            if (won()) {
                return false;
            } else {
                updateTurn();
                add(agent.choice());
                if (grid.wonGame(turn)) {
                    return false;
                } else {
                    updateTurn();
                }
            }
        } else {
            add(column);
            if (won()) {
                return false;
            } else {
                updateTurn();
            }

        }
        return true;
    }


}
