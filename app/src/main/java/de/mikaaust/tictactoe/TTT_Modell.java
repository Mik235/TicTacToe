package de.mikaaust.tictactoe;

import android.widget.Toast;


public class TTT_Modell {
    private boolean checkforwin(){
        String[][] field = new String[3][3];
        for (int i = 0; i< 3; i++){
            for(int j = 0; j<3;j++){
                field[i][j] = buttons[i][j].getText().toString();

            }
        }
        for(int i = 0; i< 3; i++){
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }
        for(int i = 0; i< 3; i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }
    private void playeronewins(){
        PlayeronePoints++;
        Toast.makeText(this,"Player 1 wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void playertwowins(){
        PlayertwoPoints++;
        Toast.makeText(this,"Player 2 wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void resetGame(){
        PlayertwoPoints = 0;
        PlayeronePoints = 0;
        updatePointsText();
        resetBoard();
    }
}
