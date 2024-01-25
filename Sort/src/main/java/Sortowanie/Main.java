package Sortowanie;

import javax.swing.*;
import java.util.Arrays;

public class Main{

    public static void main(String[] args) {
        int size = 200_000;
        int[] tablica = FunckeTablicy.wypelnijTablice(size);
        int liczbaWatkow = 4;
        int x = liczbaWatkow%size;

        int borderSize = 60*liczbaWatkow+20;
        JFrame frame = new JFrame("SORTOWANIE");
        JProgressBar[] progressBars = new JProgressBar[liczbaWatkow];

        for (int i = 0; i < liczbaWatkow; i++) {
            progressBars[i] = new JProgressBar(0, size/liczbaWatkow-x);
            progressBars[i].setBounds(50,60*i,200,40);
            frame.add(progressBars[i]);
        }

        frame.setSize(300,borderSize+10);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

        FunckeTablicy watkiSortowanie = new FunckeTablicy(tablica, liczbaWatkow, progressBars);
        int[] posortowanaTablica = watkiSortowanie.przypisanieTablicyDoWatku();

        System.out.println(Arrays.toString(posortowanaTablica));
    }
}

