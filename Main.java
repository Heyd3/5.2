package Autosalon;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Autosalon autosalon = new Autosalon();

        for (int i = 1; i < autosalon.amountAuto + 1; i++) {
            new Thread(null, autosalon::sellAuto, "Покупатель " + i).start();
        }

        new Thread(null, autosalon::receiveAuto, "Автосалон").start();


    }

}
