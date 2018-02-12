package Game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;


public class GameForm extends JFrame {

    int width;
    int height;

    boolean gamer;

    byte[][] table = new byte[6][7];

    JPanel panel = new JPanel();

    private JButton[][] fields;

    Color whoIsNext;

    enum results{ run, firstWin, secondWin, draw }


    public GameForm(int width, int height){

        this.width = width;
        this.height = height;

        gamer = true;

        initGUI();


    }



    public void setTableColumns(JButton[][] fields, int column, boolean gamer){   //a 'drop' és 'dropInForm' függvények
                                                                                  //együttes megvalósítása

        int sorSzint = 0;

        while (sorSzint > -6 && sorSzint <= 0){

            if (table[5 + sorSzint][column] != 0 || fields[6 + sorSzint][column].getBackground() != Color.pink) sorSzint--;
            else
            {
                sorSzint = -(sorSzint - 1);

                if (gamer) {

                    table[5 - (sorSzint - 1)][column] = 1;
                    fields[6 - (sorSzint - 1)][column].setBackground(Color.blue);  // az egyes játékost jelképezi a KÉK szín....
                }
                else {

                    table[5 - (sorSzint - 1)][column] = 2;
                    fields[6 - (sorSzint - 1)][column].setBackground(Color.red);        // a kettes játékost jelképezi a PIROS szín....
                }



            }

        }


    }



    public JButton[][] getFields(){

        return fields;

    }

    public void setFields(JButton[][] fields){

        this.fields = fields;

    }


    private void initGUI(){


        setSize(560, 560);
        setTitle("4-et egy sorba -> aka DOBÁLÓS AMŐBA :D");


        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Container area = getContentPane();

        GridLayout glay = new GridLayout(0, 7);

        fields = new JButton[7][7];
        area.setLayout(glay);


        // A "Tábla" feltöltése üres blokkokkal (pink háttér beállítása - kivéve legfelső oszlop, mert ott dobják be
        // az érmét a játékosok, ez világosszürke színű lesz!)

        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){


                if (i > 0) {

                    fields[i][j] = new JButton();
                    area.add(fields[i][j]);

                    fields[i][j].setBackground(Color.pink);
                    fields[i][j].setForeground(Color.darkGray);

                    fields[i][j].setBorder(BorderFactory.createLineBorder(Color.black));

                    table[i - 1][j] = 0;

                }
                else {

                    fields[i][j] = new JButton("ˇ");
                    area.add(fields[i][j]);

                    fields[i][j].setBackground(Color.lightGray);
                    fields[i][j].setForeground(Color.cyan);

                    //fields[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
                    fields[i][j].setBorderPainted(false);

                }

                //int I2 = i;
                int J2 = j;


                if (i == 0) fields[i][j].addActionListener((ActionEvent event) -> {

                    try {

                        Thread.sleep(400);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setTableColumns(fields, J2, gamer);

                    gamer = !gamer;

                    if (gamer) whoIsNext = Color.blue;   // whoIsNext változó a feladat-vázlatban szereplő 'whoIsNext' függvény
                    else whoIsNext = Color.red;          // helyett amely megadja a következő játékost, illetve annak színét!!


                });


            }

        }

    }

    /*private results whatIsResult(){

        results actResult = results.run;

        ArrayList<String> coins = new ArrayList<String>();


        for (int g = 1; g <= 2; g++){


            for (int i = 0; i < 6; i++){

                for (int j = 0; j < 7; j++){

                    coins = new ArrayList<String>();

                    if (table[i][j] == g) { coins.add(Integer.toString(i * 10) + Integer.toString(j)); howMany(j, i, g, coins); }


                }
            }

        }

        int howMany(int startX, int startY, int gamerCode, ArrayList<String> Coins){   //a metódus megszámolja az egymás melletti, alatti-fölötti, átlós azonos "érméket"...!

            //ArrayList<String> coins2 = coins;

            int x = 1;
            int y = 1;

            while (startX + x < 7 && !Coins.contains(Integer.toString((y) * 10) + Integer.toString(startX + x)) && table[startY][startX + x] == gamerCode) {


                Coins.add(Integer.toString((startY) * 10) + Integer.toString(startX + x));
                x++;

            }

            x = 1;
            y = 1;

            if () ;

            while (startX - x > 0 && !Coins.contains(Integer.toString((y) * 10) + Integer.toString(startX - x)) && table[startY][startX + x] == gamerCode) {


                Coins.add(Integer.toString((startY) * 10) + Integer.toString(startX - x));
                x++;

            }




            return Coins.size();




        }

            int coins;
            return coins;
        }


        actResult = results.run;

        return actResult;

    }*/



}
