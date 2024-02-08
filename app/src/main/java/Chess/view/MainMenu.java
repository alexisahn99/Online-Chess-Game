package Chess.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;  

import Chess.controller.*;
import Chess.model.ChessBoard;
import Chess.controller.ControllerInterface;

public class MainMenu extends JFrame implements ActionListener
{
    public static final int UPDATE_INTERVAL = 500;
    protected Timer timer;
    private ChessBoard board;
    private ControllerInterface controller;
    private WinLossData data;
    JFrame mainFrame;
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel speedPanel;
    // JPanel loadPanel;
    JPanel animationPanel;
    JPanel instructionPanel;
    JPanel recordPanel;
    JPanel decisionPanel;
    JPanel titlePanel;
    JButton onePlayer;
    JButton twoPlayer;
    // JButton loadGame;
    JButton defaultChess;
    JButton bulletChess;
    JButton blitzChess;
    JButton rapidChess;
    JButton turtleChess;
    JLabel title;
    JLabel difficultyLabel;
    JLabel instructions;
    JLabel animationLabel;
    JLabel recordLabel;
    JLabel decisionLabel;
    int time = 0;
    int wins = 0;
    int losses = 0;
    int difficulty = 1;
    int pieceCount = 0;

    public MainMenu(ChessBoard board)
    {
        this.board = board;

        mainFrame = new JFrame("Main Menu");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setBackground(new Color(119, 148, 86));

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setPreferredSize(new Dimension(450, 100));
        titlePanel.setBackground(new Color(119, 148, 86));
    
        title = new JLabel((" CHESS "), SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.PLAIN, 75));
        titlePanel.add(title);

        recordPanel = new JPanel();
        recordPanel.setPreferredSize(new Dimension(400, 40));
        recordPanel.setBackground(new Color(119, 148, 86));

        data = new WinLossData();
        int[] dataArray = data.loadData();
        for(int i = 0; i < dataArray.length; i++)
        {
            if (dataArray[i] == 1)
            {
                wins++;
            }
            else
            {
                losses++;
            }
        }

        recordLabel = new JLabel("White: " + wins + " Black: " + losses, SwingConstants.CENTER);
        recordLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        recordPanel.add(recordLabel);

        instructionPanel = new JPanel();
        instructionPanel.setLayout(new GridLayout(1,1));
        instructionPanel.setPreferredSize(new Dimension(400, 50));
        instructionPanel.setBackground(new Color(119, 148, 86));

        instructions = new JLabel("Select A Game Mode:", SwingConstants.CENTER);
        instructions.setFont(new Font("Dialog", Font.PLAIN, 24));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        instructionPanel.add(instructions);
        //difficultyLabel = new JLabel(Integer.toString(difficulty));
        //difficultyLabel.setFont(new Font("Dialog", Font.PLAIN, 40));

        speedPanel = new JPanel();
        speedPanel.setLayout(new GridLayout(1, 5));
        speedPanel.setPreferredSize(new Dimension(300, 50));
        speedPanel.setBackground(new Color(119, 148, 86));

        defaultChess = new JButton(UnicodeMap.noLimit);
        initializeDifficultyButton(defaultChess);

        bulletChess = new JButton(UnicodeMap.bullet);
        initializeDifficultyButton(bulletChess);

        blitzChess = new JButton(UnicodeMap.blitz);
        initializeDifficultyButton(blitzChess);

        rapidChess = new JButton(UnicodeMap.rapid);
        initializeDifficultyButton(rapidChess);

        turtleChess = new JButton(UnicodeMap.turtle);
        initializeDifficultyButton(turtleChess);

        speedPanel.add(defaultChess);
        speedPanel.add(bulletChess);
        speedPanel.add(blitzChess);
        speedPanel.add(rapidChess);
        speedPanel.add(turtleChess);
        
        decisionPanel = new JPanel();
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridLayout(1, 1));
        decisionPanel.setPreferredSize(new Dimension(500, 50));
        decisionPanel.setBackground(new Color(119, 148, 86));

        decisionLabel = new JLabel("Default - No Time Limits", SwingConstants.CENTER);
        decisionLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        decisionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        decisionPanel.add(decisionLabel);

        // loadPanel = new JPanel();
        // loadPanel.setLayout(new GridLayout(1, 1));
        // loadPanel.setPreferredSize(new Dimension(150, 50));
        // loadPanel.setBackground(new Color(119, 148, 86));

        // loadGame = new JButton("Load Saved Game");
        // loadGame.addActionListener(this);
        // loadGame.setEnabled(false);     //Work on this button in deliverable 2
        // loadPanel.add(loadGame);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(300, 100));

        onePlayer = new JButton("Player vs Computer");
        initializeButton(onePlayer);
        onePlayer.setEnabled(false);        //Something to work on later on when we integrate a chess computer
        buttonPanel.add(onePlayer);

        twoPlayer = new JButton("Player vs Player");
        initializeButton(twoPlayer);
        buttonPanel.add(twoPlayer);

        animationPanel = new JPanel();

        animationPanel.setPreferredSize(new Dimension(425, 100));
        animationPanel.setBackground(new Color(119, 148, 86));

        timer = new Timer(UPDATE_INTERVAL, this);
        timer.start();

        mainPanel.add(titlePanel);
        mainPanel.add(recordPanel);
        mainPanel.add(instructionPanel);
        mainPanel.add(speedPanel);
        mainPanel.add(decisionPanel);
        mainPanel.add(buttonPanel);
        // mainPanel.add(loadPanel);
        mainPanel.add(animationPanel);

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == timer)
        {
            pieceCount++;
            String piece = UnicodeMap.getRandomPiece();
            animationLabel = new JLabel(piece);
            animationLabel.setFont(new Font("Dialog", Font.BOLD, 30));
            animationPanel.add(animationLabel);

            if (pieceCount <= 10)
            {
                animationPanel.revalidate();
                animationPanel.repaint();
            }
            else
            {
                timer.stop();
            }
        }

        else if (event.getSource() == this.defaultChess)
        {
            decisionLabel.setText("Default - No Time Limits");
            this.time = 0;
        }
        else if (event.getSource() == this.bulletChess)
        {
            decisionLabel.setText("Bullet - Each Player will get 1 minute");
            this.time = 1;
        }

        else if (event.getSource() == this.blitzChess)
        {
            decisionLabel.setText("Blitz - Each Player will get 3 minutes");
            this.time = 3;
        }

        else if (event.getSource() == this.rapidChess)
        {
            decisionLabel.setText("Rapid - Each Player will get 10 minutes");
            this.time = 10;
        }

        else if (event.getSource() == this.turtleChess)
        {
            decisionLabel.setText("Turtle - Each Player will get 30 minutes");
            this.time = 30;
        }

        else if(event.getSource() == this.twoPlayer)
        {
            ControllerInterface controller = new ChessControllerTwoPlayer(board, this.time);
            mainFrame.setVisible(false);
        }

        decisionPanel.revalidate();
        decisionPanel.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void initializeDifficultyButton(JButton button)
    {
        button.setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
        button.setPreferredSize(new Dimension(50,50));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
    }

    public void initializeButton(JButton button)
    {
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
