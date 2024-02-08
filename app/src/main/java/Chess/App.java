package Chess;

import Chess.model.ChessBoard;
import Chess.view.MainMenu;

public class App {
    public static void main(String[] args) {
        ChessBoard model = new ChessBoard();
        MainMenu menu = new MainMenu(model);
    }
}
