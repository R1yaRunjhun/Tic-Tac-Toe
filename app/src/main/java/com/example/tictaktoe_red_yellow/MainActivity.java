package com.example.tictaktoe_red_yellow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;
    int[]gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPostions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{6,4,2}};
    Boolean gameInActive=true;
    public void dropIn(View view)
    {
        ImageView Counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(Counter.getTag().toString());
        if(gameState[tappedCounter]==2&&gameInActive)
        {
            gameState[tappedCounter]=activePlayer;
            if (activePlayer == 0)
            {
                Counter.setImageResource(R.drawable.yellow);
                Counter.setTranslationY(-1000f);
                Counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
                activePlayer = 1;
            }
            else
                {
                Counter.setImageResource(R.drawable.red);
                    Counter.setTranslationX(-1000f);
                    Counter.animate().translationXBy(1000f).rotation(360).setDuration(300);
                activePlayer = 0;
                }


            for(int [] winningPosition :winningPostions )
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=2)
                {
                    gameInActive=false;
                    String winner="Red";
                    if(gameState[winningPosition[0]]==0)
                    {
                        winner="Yellow";
                    }
                    TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+" has Won!!");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameIsOver=true;
                    for(int countState:gameState)
                    {
                        if(countState==2) gameIsOver=false;
                    }
                    if(gameIsOver)
                    {
                        TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw!!");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }

    public void again(View view){

        Intent i=new Intent(MainActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
        public void playAgain(View view)
        {
            gameInActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
