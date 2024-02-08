package Chess.view;

import java.awt.*;
import javax.swing.*; 

import Chess.controller.ControllerInterface;
import Chess.model.ChessPieces.ChessPieceColor;

public class GameDisplay extends JLabel
{
    private ChessPieceColor currentPlayer;
    private ControllerInterface controller;

    public GameDisplay(ControllerInterface controller)
    {
        this.controller = controller;
        this.currentPlayer = this.controller.getCurrentPlayer();
    }

    public String displayGameStatus()
    {
        this.currentPlayer = this.controller.getCurrentPlayer();
        if (currentPlayer == ChessPieceColor.W)
        {
            return "White";
        }
        else
        {
            return "Black";
        }
    }
    
    public void invalidMove()
    {
        setText("No Valid Moves. Select A New Piece");
    }
}
