package br.edu.exemplo;

public class BubbleSort {

    private int[] numeros;

    public BubbleSort(int[] numeros) {
        this.numeros = numeros;
    }

    /**
     * Bubble Sort: a cada passada, vizinhos fora de ordem são trocados e o maior
     * valor ainda não posicionado "borbulha" até o fim do vetor.
     * Se uma passada termina sem trocas, o vetor já está ordenado.
     */
    public void ordenar() {
        int fim = numeros.length - 1;
        boolean houveTroca = true;

        while (houveTroca && fim > 0) {
            houveTroca = false;
            for (int i = 0; i < fim; i++) {
                if (numeros[i] > numeros[i + 1]) {
                    trocar(i, i + 1);
                    houveTroca = true;
                }
            }
            fim--;
        }
    }

    private void trocar(int i, int j) {
        int aux = numeros[i];
        numeros[i] = numeros[j];
        numeros[j] = aux;
    }

    public int[] getNumeros() {
        return numeros;
    }
}
