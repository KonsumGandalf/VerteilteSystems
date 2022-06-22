package Development.uebung04.a_messager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageStore {

    private Map<String, List<Message>> messages = new TreeMap<>();
    private Lock monitor = new ReentrantLock(); // A ReentrantLock is unstructured, unlike synchronized constructs --
    // i.e. you don't need to use a block structure for locking and can even hold a lock across methods.

    public void MessageStore(){  }

    public boolean registerUser(String username){
        try {
            monitor.lock();
            if (messages.containsKey(username)) return false;
            else {
                messages.put(username, new LinkedList<>());
                return true;
            }
        } finally {
            monitor.unlock();
        }
    }

    /**
     *
     * @param content - text send
     * @param senderName - name of the sender
     * @param toName - name of the text receiver
     * @return false - for errors - true - for successful new msg
     */
    public boolean sendMsg(String content, String senderName, String toName) {
        Message msg = new Message(content, senderName, toName);
        try {
            monitor.lock();
            if (!messages.containsKey(senderName) || !messages.containsKey(toName)) {
                return false;
            }
            messages.get(toName).add(msg);
            return true;
        } finally {
            monitor.unlock();
        }
    }

    public List<Message> getNewMessages(String toName){
        try{
            monitor.lock();
            List<Message> userMsg = messages.get(toName);
            messages.replace(toName, new LinkedList<>());
            return userMsg;
        } finally {
            monitor.unlock();
        }
    }


}
