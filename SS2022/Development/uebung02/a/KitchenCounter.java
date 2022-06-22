package Development.uebung02.a;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {
    private Deque<Food> foodTable = new LinkedList<>();
    // private Lock kitchenLock = new ReentrantLock();
    // private Condition kitchenCon = kitchenLock.newCondition();
    private Lock tableLock = new ReentrantLock();
    private Condition fullTableCon = tableLock.newCondition();
    private Condition emptyTableCon = tableLock.newCondition();
    // private Lock foodLock = new ReentrantLock();
    // private Condition foodCon = foodLock.newCondition();

    private int tableSize;
    KitchenCounter(int tableSize){
        this.tableSize = tableSize;
    }

    public void put(){
        Food newFood = new Food();
        try{
            tableLock.lock();
            while(this.foodTable.size() == this.tableSize){
                System.out.println("no capacity");
                fullTableCon.await();
            }
            foodTable.addLast(newFood);
            System.out.println(newFood + " was placed.");
            emptyTableCon.signalAll();
        }
        catch (InterruptedException e) { e.printStackTrace();}
        finally{ tableLock.unlock(); }

    }

    public Food take(){
        try{
            tableLock.lock();
            while(this.foodTable.size() == 0){
                System.out.println("no food available");
                emptyTableCon.await();
            }
            return foodTable.pollFirst();
        }
        catch (InterruptedException e) { e.printStackTrace();}
        finally{
            fullTableCon.signalAll();
            tableLock.unlock();
        }
        return null; // when would this occur in a catch case?
    }
}
