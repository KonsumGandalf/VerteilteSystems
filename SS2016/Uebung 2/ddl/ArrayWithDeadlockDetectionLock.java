import java.util.Random;

/**
 * Wrapperklasse fuer Integerarrays, die durch ein deadlockfreies Schloss
 * geschuetzt werden
 */
public class ArrayWithDeadlockDetectionLock {
    private int[] array;
    private DeadlockDetectingLock lock;

    /**
     * Konstruktor
     */
    public ArrayWithDeadlockDetectionLock(int[] array) {
        this.array = array;
        this.lock = new DeadlockDetectingLock();
    }

    /**
     * Schloss anfordern und bei Erhalten das Array
     * zurueckgeben.
     */
    public int[] getArrayAndLock() {
    		this.lock.lock();
        return this.array;
    }

    /**
     * Schloss freigeben
     */
    public void unlock() {
        this.lock.unlock();
    }
}
