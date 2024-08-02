import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    JFrame frame =new JFrame("TIC_TAC_TOE");
    JLabel txt = new JLabel();
    JPanel txtPanel = new JPanel();
    JPanel bPanel = new JPanel();
    JButton[][] board =new JButton[3][3];
    String playX ="X";
    String playY="O";
    String currPlay = playX;
    boolean gameStat = false;
    int turns = 0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(600, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        txt.setBackground(Color.darkGray);
        txt.setForeground(Color.white);
        txt.setFont(new Font("Arial", Font.BOLD, 50));
        txt.setText("TIC TAC TOE");
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setOpaque(true);  

        txtPanel.setLayout(new BorderLayout());
        txtPanel.add(txt);
        frame.add(txtPanel,BorderLayout.NORTH);

        bPanel.setLayout(new GridLayout(3,3));
        bPanel.setBackground(Color.darkGray);
        frame.add(bPanel,BorderLayout.CENTER);

        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                JButton cell =new JButton();
                board[r][c]= cell;
                bPanel.add(cell);
                cell.setBackground(Color.darkGray);
                cell.setForeground(Color.orange);
                cell.setFont(new Font("Arial", Font.BOLD, 120));
                cell.setFocusable(false);

                cell.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameStat)return;
                        JButton cell = (JButton)e.getSource();
                        if(cell.getText()==""){
                            cell.setText(currPlay);
                            turns++;
                            checkWin();
                            if(!gameStat){
                                currPlay=currPlay==playX?playY:playX;
                                txt.setText(currPlay+"'s turn.");
                            }
                        }
                    }
                });
            }
        }

    }



    void checkWin(){
        for(int r=0;r<3;r++){
            if(board[r][0].getText()=="")continue;
            if (board[r][0].getText() == board[r][1].getText() &&
            board[r][1].getText() == board[r][2].getText()) {
                for(int i=0;i<3;i++){
                    setWin(board[r][i]);
                }
                gameStat=true;
                return;
            }
        }
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;
            
            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWin(board[i][c]);
                }
                gameStat = true;
                return;
            }
        }
        if (board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWin(board[i][i]);
            }
            gameStat = true;
            return;
        }
        if (board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != "") {
            setWin(board[0][2]);
            setWin(board[1][1]);
            setWin(board[2][0]);
            gameStat = true;
            return;
        }
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameStat = true;
        }
    }

    void setWin(JButton cell){
        cell.setBackground(Color.gray);
        cell.setForeground(Color.green);
        txt.setText(currPlay+" is the Winner!");
        restartGame();
    }

    void setTie(JButton cell){
        cell.setBackground(Color.gray);
        cell.setForeground(Color.red);
        txt.setText("Tie!");
        restartGame();
    }
    void restartGame(){
        JButton restart=new JButton();
        restart.setBackground(Color.darkGray);
        restart.setForeground(Color.white);
        restart.setFont(new Font("Arial",Font.BOLD, 50));
        restart.setText("RESTART");
        restart.setFocusable(false);
        frame.add(restart,BorderLayout.SOUTH);
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new TicTacToe();
            }
        });
    }
}
