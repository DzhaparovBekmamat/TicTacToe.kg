package com.example.gameapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    private final Button[][] buttons = new Button[3][3];

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout);


        game = new Game();
        buildGameField();
        game.start();
    }

    private void buildGameField() {
        Square[][] field = game.getField();
        for (int i = 0, lenI = field.length; i < lenI; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0, lenJ = field[i].length; j < lenJ; j++) {
                Button button = new Button(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i, j));
                row.setGravity(Gravity.CENTER);
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                button.setWidth(300);
                button.setHeight(300);
                button.setBackgroundResource(R.drawable.button);
            }
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public class Listener implements View.OnClickListener {
        private final int x;
        private final int y;

        Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            Game g = game;
            Player player = g.getCurrentActivePlayer();
            if (makeTurn(x, y)) {
                button.setText(player.getName());
            }
            Player winner = g.checkWinner();
            if (winner != null) {
                gameOver(winner);
            }
            if (g.isFieldFilled()) {
                gameOver();
            }
        }
    }

    private void gameOver(Player player) {
        CharSequence text = "ИГРОК \"" + player.getName() + "\" ВЫИГРАЛ!";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private void gameOver() {
        CharSequence text = "НИЧЬЯ,РЕБЯТА!";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private boolean makeTurn(int x, int y) {
        return game.makeTurn(x, y);
    }

    private void refresh() {
        Square[][] field = game.getField();

        for (int i = 0, len = field.length; i < len; i++) {
            for (int j = 0, len2 = field[i].length; j < len2; j++) {
                if (field[i][j].getPlayer() == null) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(field[i][j].getPlayer().getName());
                }
            }
        }
    }
}