public class TestDeadlockDetectionLock {

    /**
     * Summiert zwei Array mit deadlockfreien Schloessern auf.
     */
    public static int sumArrays(ArrayWithDeadlockDetectionLock a1, ArrayWithDeadlockDetectionLock a2) {
        int value = 0;
        
        int[] array1 = a1.getArrayAndLock();
        int[] array2 = a2.getArrayAndLock();
        
        if (array1.length != array2.length)
            return 0;

        for (int i = 0; i < array1.length; ++i)
            value += array1[i] + array2[i];

        a1.unlock();
        a2.unlock();

        return value;
    }

    /**
     * Testet sumArrays mit mehreren Threads
     */
    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5 };
        int[] b = { 6, 7, 8, 9, 10 };

        final ArrayWithDeadlockDetectionLock aSafe = new ArrayWithDeadlockDetectionLock(a);
        final ArrayWithDeadlockDetectionLock bSafe = new ArrayWithDeadlockDetectionLock(b);

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("a+b = " + sumArrays(aSafe, bSafe));
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("b+a = " + sumArrays(bSafe, aSafe));
            }
        });

        thread1.start();
        thread2.start();
    }
}