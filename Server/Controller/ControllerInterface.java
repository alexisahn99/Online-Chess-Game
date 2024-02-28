package Chess.controller;

import Chess.model.ChessPieces.ChessPieceColor;

public interface ControllerInterface 
{
    /**
     * Handles selection of a chess board square.
     * 
     * @param row
     * @param col
     */
    public void userPressed(int row, int col);
    public void selectPiece(int fromRow, int fromCol);
    public void selectDestination(int toRow, int toCol);
    public void switchPlayers();

    /**
     * Handles user quitting, including saving the game state.
     */
    public void userQuit();
    public ChessPieceColor getCurrentPlayer();
    public void passTime();
    public void convertPawn(String unicode, ChessPieceColor color);    
}
