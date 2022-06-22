import java.util.*;
import java.util.concurrent.locks.*;

/* The purpose of this class is to test for deadlocks. The lock()
 * method now throws a DeadlockDetectedException, if a deadlock occurs.
 */
@SuppressWarnings("serial")
public class DeadlockDetectingLock extends ReentrantLock {
    private boolean debugging;  // verbose log flag
    // List of all locks:
    private static List deadlockLocksRegistry = new ArrayList();
    // List of threads (hard) waiting for THIS lock.
    private List hardwaitingThreads = new ArrayList();

    private static synchronized void 
    registerLock(DeadlockDetectingLock ddl)  {
        if (!deadlockLocksRegistry.contains(ddl))
             deadlockLocksRegistry.add(ddl);
    }

    private static synchronized void 
    unregisterLock(DeadlockDetectingLock ddl)  {
        if (deadlockLocksRegistry.contains(ddl))
             deadlockLocksRegistry.remove(ddl);
    }

    private static synchronized void 
    markAsHardwait(List l, Thread t)  {
        if (!l.contains(t)) l.add(t);
    }

    private static synchronized void 
    freeIfHardwait(List l, Thread t)  {
        if (l.contains(t)) l.remove(t);
    }

    // Given a thread, return all locks that are already owned
    private static Iterator 
    getAllLocksOwned(Thread t)  {
        DeadlockDetectingLock current;
        ArrayList results = new ArrayList( );
        Iterator itr = deadlockLocksRegistry.iterator( );
        while (itr.hasNext( )) {
            current = (DeadlockDetectingLock) itr.next();
            if (current.getOwner( ) == t) 
            	results.add(current);
        }
        return results.iterator( ); 
    }

    // Given a lock, return all threads that are (hard) waiting for the lock
    private static Iterator getAllThreadsHardwaiting(DeadlockDetectingLock l) {
        return l.hardwaitingThreads.iterator();
    }

    // Check to see if a thread can perform a hard wait on a lock
    private static boolean 
    canThreadWaitOnLock0(Thread t, DeadlockDetectingLock l) {
        Iterator locksOwned = getAllLocksOwned(t);
        while (locksOwned.hasNext()) {
            DeadlockDetectingLock current = (DeadlockDetectingLock) locksOwned.next();
            // Thread can't wait if lock is already owned. 
            // This is the end condition for the recursive algorithm
            if (current == l) return false;

            Iterator waitingThreads = getAllThreadsHardwaiting(current);
            while (waitingThreads.hasNext()) {
                Thread otherthread = (Thread) waitingThreads.next();
                // In order for the thread to safely wait on the lock, it can't
                // own any locks that have waiting threads that already owns
                // lock. etc. etc. etc. recursively etc.
                if (!canThreadWaitOnLock0(otherthread, l)) {
                    return false;
                }
            }
        }
        return true;
    }

    // synchronized wrap arround the recursive depth first search
    private static synchronized boolean 
    canThreadWaitOnLock(Thread t, DeadlockDetectingLock l) {
        if (l.getOwner() == null) {
            return true;
        }
        return canThreadWaitOnLock0(t,l);
    }

    public DeadlockDetectingLock( )  {
        this(false, false);
    }

    public DeadlockDetectingLock(boolean fair)  {
        this(fair, false);
    }

    public DeadlockDetectingLock(boolean fair, boolean debug)  {
        super(fair);
        debugging = debug;
        registerLock(this);
    }

    private static boolean DDLdeadlockDETECTED = false;

    public void lock( )  {
        if (DDLdeadlockDETECTED)  {
            throw new RuntimeException("EARILER DEADLOCK DETECTED");
        }
        if (isHeldByCurrentThread()) {
            if (debugging) System.out.println("Already Own Lock");
            super.lock( );
            freeIfHardwait(hardwaitingThreads, Thread.currentThread());
            return;
        }
        markAsHardwait(hardwaitingThreads, Thread.currentThread());
        if (canThreadWaitOnLock(Thread.currentThread(), this)) {
            if (debugging) System.out.println("Waiting For Lock");
            super.lock();
            freeIfHardwait(hardwaitingThreads, Thread.currentThread());
            if (debugging) System.out.println("Got New Lock");
        } else {
            DDLdeadlockDETECTED = true;
            throw new RuntimeException("DEADLOCK DETECTED");
        }
    }
    // note: this code is for demonstration use only
    //       a production quality version must also override
    //       the alternative lock methods (lockInterruptably, tryLock etc.)
    //       and offer a DDL-version of the Condition/await-mechanism
    // A full DDL-implementation can be found on http://OnJava.com
 
    // Test:
    private static Lock a = new DeadlockDetectingLock(false, true);
    private static Lock b = new DeadlockDetectingLock(false, true);

    private static void delaySeconds(int seconds) {
        try {
             Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
        }
    }

 
    public static void main(String args[ ]) {
 		new Thread(new Runnable( ) {
             public void run( ) {
                 System.out.println("thread one grab lock a");
                 a.lock( );
                 delaySeconds(2);
                 System.out.println("thread one grab lock b");
                 b.lock( );
                 delaySeconds(2);
                 a.unlock( ); b.unlock( );
             }
         }).start( );

         new Thread(new Runnable( ) {
             public void run( ) {
                 System.out.println("thread two grab lock b");
                 b.lock( );
                 delaySeconds(2);
                 System.out.println("thread two grab lock a");
                 a.lock( );
                 delaySeconds(2);
                 a.unlock( ); b.unlock( );
             }
         }).start( );
    }
    
    public Thread gibOwner() {
    		return super.getOwner();
    }
}