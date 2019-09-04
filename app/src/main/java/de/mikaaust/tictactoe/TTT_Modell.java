package de.mikaaust.tictactoe;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


    public class TTT_Modell {
        public TTT_Modell(boolean playeroneturn, int roundcount, int playeronePoints, int playertwoPoints, TextView textViewPlayerone, TextView textViewPlayertwo) {
            this.playeroneturn = playeroneturn;
            this.roundcount = roundcount;
            PlayeronePoints = playeronePoints;
            PlayertwoPoints = playertwoPoints;
            this.textViewPlayerone = textViewPlayerone;
            this.textViewPlayertwo = textViewPlayertwo;
        }

        private Button[][] buttons = new Button[3][3];

        private boolean playeroneturn = true;

        private int roundcount;

        private int PlayeronePoints;
        private int PlayertwoPoints;

        private TextView textViewPlayerone;
        private TextView textViewPlayertwo;
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
        Toast.makeText(this,"Spieler 1 Gewinnt",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void playertwowins(){
        PlayertwoPoints++;
        Toast.makeText(this,"Spieler 2 Gewinnt",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    public  void Gamerst(){
        MainActivity.setPlayertwoPoints(0);
        MainActivity.setPlayeronePoints(0);
        updatePointsText();
        resetBoard();
    }
        private void resetBoard(){
            for(int i = 0; i<3;i++){
                for (int j=0;j<3;j++){
                    buttons[i][j].setText("");
                }
            }

            roundcount=0;
            playeroneturn = true;

        }
        private void updatePointsText(){
            textViewPlayerone.setText("Spieler 1: " + PlayeronePoints);
            textViewPlayertwo.setText("Spieler 2: " + PlayertwoPoints);
        }
}
