package de.mikaaust.tictactoe;

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
                TTT_Modell.resetGame();
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





    private void draw(){
        Toast.makeText(this,"Draw",Toast.LENGTH_LONG).show();
        resetBoard();
    }
    private void updatePointsText(){
        textViewPlayerone.setText("Spieler 1: " + PlayeronePoints);
        textViewPlayertwo.setText("Spieler 2: " + PlayertwoPoints);
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
