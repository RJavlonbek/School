import java.util.Iterator;
import java.util.LinkedList;


public abstract class ActionBase {
    
	Iterator<String> iter;
	
    abstract void action(LinkedList<String> list);
    
    public String manual() {
        return "No manual defined...";
    }
    
}
