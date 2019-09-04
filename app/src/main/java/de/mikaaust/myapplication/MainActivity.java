package de.mikaaust.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button[][] buttons = new Button[3][3];

    private boolean playeroneturn = true;

    private int roundcount;

    private int PlayeronePoints;
    private int PlayertwoPoints;

    private TextView textViewPlayerone;
    private TextView textViewPlayertwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayerone = findViewById(R.id.text_view_p1);
        textViewPlayertwo = findViewById(R.id.text_view_p2);

        for (int i = 0; i< 3; i++){
            for(int j = 0; j<3;j++){
                String buttonID = "button_" + i +j ;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(!((Button) view).getText().toString().equals("")){
            return;
        }
        if(playeroneturn){
            ((Button) view).setText("X");
        }else{
            ((Button)view).setText("O");
        }

        roundcount++;
        if(checkforwin()){
            if(playeroneturn){
                playeronewins();
            }else{
                playertwowins();
            }
        }else if(roundcount == 9){
            draw();

        }else{
            playeroneturn = !playeroneturn;
        }
    }

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

    private void draw(){
        Toast.makeText(this,"Draw",Toast.LENGTH_LONG).show();
        resetBoard();
    }
    private void updatePointsText(){
        textViewPlayerone.setText("Player 1: " + PlayeronePoints);
        textViewPlayertwo.setText("Player 2: " + PlayertwoPoints);
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

    private void resetGame(){
        PlayertwoPoints = 0;
        PlayeronePoints = 0;
        updatePointsText();
        resetBoard();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundpoint", roundcount);
        outState.putInt("playeronepoints",PlayeronePoints);
        outState.putInt("playertwopoints",PlayertwoPoints);
        outState.putBoolean("playeroneturn",playeroneturn);
    }

    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        roundcount = saveInstanceState.getInt("roundcount");
        PlayeronePoints = saveInstanceState.getInt("playeronepoints");
        PlayertwoPoints = saveInstanceState.getInt("Playertwopoints");
        playeroneturn = saveInstanceState.getBoolean("playeroneturn");
    }

}
