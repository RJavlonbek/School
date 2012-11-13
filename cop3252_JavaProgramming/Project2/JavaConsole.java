import java.util.LinkedList;
import java.util.Scanner;

public class JavaConsole {
	
	public static void main(String[] args) {
		
		Echo echo = Echo.getInstance();
		Draw draw = Draw.getInstance();
		Math math = Math.getInstance();
		Dice dice = Dice.getInstance();
		
		Scanner in = new Scanner(System.in);
		LinkedList<String> list = new LinkedList<String>();
		
		String userInput = "";
		boolean running = true;
		
		while(running){
			userInput = "";
			String temp;
			temp = "";
			System.out.print("JavaConsole>");
			
			userInput = in.next();
		
			temp = in.nextLine();
			Scanner s = new Scanner(temp);
			
			while(s.hasNext()) {
				list.add(s.next());
			}
			
			if(userInput.compareTo("exit")==0 || userInput.compareTo("quit")==0) {
				System.out.println("See you Later!");
				running = false; 
				//break;
			} else if (userInput.compareTo("echo") == 0) {
				echo.action(list);
			} else if (userInput.compareTo("dice") == 0) {
				dice.action(list);
			} else if (userInput.compareTo("draw") == 0) {
				draw.action(list);
			}else if(userInput.compareTo("math") == 0) 
			{
				math.action(list);
			}else {
				System.out.println("Invalid Command, try again!");
			}
			list.clear();
			
		}

	}

}
