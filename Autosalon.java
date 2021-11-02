package Autosalon;

import java.util.ArrayList;
import java.util.List;

public class Autosalon {
    List<Auto> auto = new ArrayList<>(10);
    int amountAuto = 10;

    List<Auto> getAuto() {
        return auto;
    }

    public void receiveAuto() {
        int i = 0;
        while (i != amountAuto) {
            try {
                AddAuto();
                System.out.println("Производитель выпустил 1 авто");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private synchronized void AddAuto() throws InterruptedException {
        getAuto().add(new Auto());
        notify();
        Thread.sleep(3000);

    }

    public synchronized Auto sellAuto() {
        try {

            System.out.println(Thread.currentThread().getName() + " зашел в салон");
            while (getAuto().size() == 0) {
                System.out.println("Машин нет");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getAuto().remove(0);
    }
}