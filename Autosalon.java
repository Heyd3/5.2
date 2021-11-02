package Autosalon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Autosalon {
    List<Auto> auto = new ArrayList<>(10);
    int amountAuto = 10;


    List<Auto> getAuto() {
        return auto;
    }

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void receiveAuto() {
        int i = 0;
        while (i != amountAuto) {
            lock.lock();
            getAuto().add(new Auto());
            condition.signal();
            lock.unlock();
            try {
                Thread.sleep(3000);
                System.out.println("Производитель выпустил 1 авто");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private void AddAuto() throws InterruptedException {
        getAuto().add(new Auto());
        condition.signalAll();
        Thread.sleep(3000);

    }

    public Auto sellAuto() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в салон");
            while (getAuto().size() == 0) {
                System.out.println("Машин нет");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return getAuto().remove(0);
    }
}