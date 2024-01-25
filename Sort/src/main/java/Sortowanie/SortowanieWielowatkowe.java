package Sortowanie;


import javax.swing.*;
import java.lang.Thread;

public class SortowanieWielowatkowe extends Thread {
    public int[] czesci;
    public JProgressBar progressBar;

    public SortowanieWielowatkowe(int[] czesci, JProgressBar progressBar) {
        this.czesci = czesci;
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        bubbleSort(czesci);
    }

    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            progressBar.setValue(i);
        }
    }

}