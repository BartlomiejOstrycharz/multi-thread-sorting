package Sortowanie;

import javax.swing.*;
import java.util.Random;


public class FunckeTablicy{
    public int[] tablica;
    public int liczbaWatkow;
    public JProgressBar[] progressBars;

    public FunckeTablicy(int[] talica, int liczbaWatkow, JProgressBar[] progressBars) {
        this.tablica = talica;
        this.liczbaWatkow = liczbaWatkow;
        this.progressBars = progressBars;
    }


    public static int[] wypelnijTablice(int x){
        int[] tablica = new int[x];
        Random random = new Random();
        for(int i=0;i<x;i++){
            tablica[i] = random.nextInt(100)+1;
        }
        return tablica;
    }

    public int[][] podzielTablice(int[] tablica, int liczbaWatkow) {
        int[][] podzielonaTablica = new int[liczbaWatkow][];
        int rozmiarCzesci = tablica.length / liczbaWatkow;
        int reszta = tablica.length % liczbaWatkow;
        int znacznik = 0;

        for (int i = 0; i < liczbaWatkow; i++) {
            int rozmiar = rozmiarCzesci + (i < reszta ? 1 : 0);

            podzielonaTablica[i] = new int[rozmiar];
            System.arraycopy(tablica, znacznik, podzielonaTablica[i], 0, rozmiar);
            znacznik += rozmiar;
        }
        return podzielonaTablica;
    }


    public int[] przypisanieTablicyDoWatku() {
        int[][] czesci = podzielTablice(tablica, liczbaWatkow);
        Thread[] watekSortujacy = new Thread[liczbaWatkow];

        for (int i = 0; i < liczbaWatkow; i++) {
            watekSortujacy[i] = new Thread(new SortowanieWielowatkowe(czesci[i], progressBars[i]));
            watekSortujacy[i].start();
        }

        for (int i = 0; i < liczbaWatkow; i++) {
            try {
                watekSortujacy[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return polaczTablice(czesci);
    }

    public int[] polaczTablice(int[][] podzielonaTablica) {
        int[] polaczonaTablica = new int[tablica.length];
        int[] indexTablicy = new int[podzielonaTablica.length];

        for (int i = 0; i < tablica.length; i++) {
            int min = Integer.MAX_VALUE;
            int index = 0;

            for (int j = 0; j < podzielonaTablica.length; j++) {
                if (indexTablicy[j] < podzielonaTablica[j].length && podzielonaTablica[j][indexTablicy[j]] < min) {
                    min = podzielonaTablica[j][indexTablicy[j]];
                    index = j;
                }
            }

            polaczonaTablica[i] = min;
            indexTablicy[index]++;
        }

        return polaczonaTablica;
    }

}
