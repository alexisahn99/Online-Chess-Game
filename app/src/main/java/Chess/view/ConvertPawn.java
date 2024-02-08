package Chess.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Chess.model.ChessPieces.ChessPieceColor;
import Chess.controller.ControllerInterface;

public class ConvertPawn extends JFrame implements ActionListener
{
    private JButton knightButton;
    private JButton bishopButton;
    private JButton rookButton;
    private JButton queenButton;
    private JFrame mainFrame;
    private JPanel convertPanel;
    private ChessPieceColor color;
    private ControllerInterface controller;
    private String passPiece;

    public ConvertPawn(ChessPieceColor color, ControllerInterface controller)
    {
        this.controller = controller;
        mainFrame = new JFrame("Select A New Piece");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 


        convertPanel = new JPanel();
        convertPanel.setLayout(new GridLayout(1,4));
        convertPanel.setSize(400, 50);
        
        this.color = color;
        if (this.color == ChessPieceColor.W)
        {
            knightButton = new JButton(UnicodeMap.wKnight);
            bishopButton = new JButton(UnicodeMap.wBishop);
            rookButton = new JButton(UnicodeMap.wRook);
            queenButton = new JButton(UnicodeMap.wQueen);
        }
        else
        {
            knightButton = new JButton(UnicodeMap.bKnight);
            bishopButton = new JButton(UnicodeMap.bBishop);
            rookButton = new JButton(UnicodeMap.bRook);
            queenButton = new JButton(UnicodeMap.bQueen);
        }
        initSwapButtons(convertPanel, knightButton);
        initSwapButtons(convertPanel, bishopButton);
        initSwapButtons(convertPanel, rookButton);
        initSwapButtons(convertPanel, queenButton);

        mainFrame.add(convertPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void initSwapButtons(JPanel panel, JButton button)
    {
        button.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
        button.setPreferredSize(new Dimension(50,50));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == this.knightButton)
        {
            if (this.color == ChessPieceColor.W)
            {
                this.passPiece = UnicodeMap.wKnight;
            }
            else
            {
                this.passPiece = UnicodeMap.bKnight;
            }
        }
        else if (event.getSource() == this.bishopButton)
        {
            if (this.color == ChessPieceColor.W)
            {
                this.passPiece = UnicodeMap.wBishop;
            }
            else
            {
                this.passPiece = UnicodeMap.bBishop;
            }
        }
        else if (event.getSource() == this.rookButton)
        {
            if (this.color == ChessPieceColor.W)
            {
                this.passPiece = UnicodeMap.wRook;
            }
            else
            {
                this.passPiece = UnicodeMap.bRook;
            }
        }
        else                               
        {
            if (this.color == ChessPieceColor.W)
            {
                this.passPiece = UnicodeMap.wQueen;
            }
            else
            {
                this.passPiece = UnicodeMap.bQueen;
            }
        }
        this.mainFrame.dispose();
        this.controller.convertPawn(this.passPiece, this.color);
    }
}
