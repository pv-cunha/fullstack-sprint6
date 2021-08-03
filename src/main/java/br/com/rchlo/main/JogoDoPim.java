package br.com.rchlo.main;

public class JogoDoPim {

    public static void main(String[] args) {
        jogoDoPim(3);
    }

    private static void jogoDoPim(int entrada) {
        for (int i = 1; i <= entrada; i++) {
            if (i % 4 == 0) {
                System.out.println("PIM");
            } else {
                System.out.println(i);
            }
        }
    }
}
