import java.util.LinkedList;

public class Math extends ActionBase {

	private static Math _singletonInstance = new Math();

	private Math() {

	}

	public static Math getInstance() {
		return _singletonInstance;
	}
	/* Example Inputs:
	 *  'math -a 234 2 1 7 3'
	 *  'math -x 2 5 3'
	 *  
	 *  'math -m'
	 */ 
	
	
	@Override
	public void action(LinkedList<String> list) {

		try {
			if (list.peekFirst().startsWith("-")) {

				String parm = list.removeFirst();
				iter = list.iterator();

				int sum = 0;
				switch (parm.charAt(1)) {
				case 'a':
					try {
						while (iter.hasNext()) {
							sum += Integer.parseInt(iter.next());
						}
						System.out.println("The sum of all the numbers is " + sum);
					}catch(NumberFormatException ex) {
						System.out.println("Number Format Error");
					}
					break;
				case 'x':
					try {
						sum = 0;
						if(iter.hasNext())
							sum = 1;
						while (iter.hasNext()) {
							sum *= Integer.parseInt(iter.next());
						}
						System.out.println("Multiplied together, all the numbers equal " + sum);
					}catch(NumberFormatException ex) {
						System.out.println("Number Format Error");
					}
					break;
				case 'm':
					System.out.println(this.manual());
					break;
				default:
					System.out.println("Unknown parameter...see man page");
					break;
				}

			} else {
				System.out.println("Error in input");
				System.out.println(manual());
			}
		} catch (NullPointerException ex) {
			System.out.println("Null Pointer Error, please follow math with a command and numbers or type 'math -m' for help");
		}

	}

	@Override
	public String manual() {
		return "This command performs math operations on the numbers following the command 'math'\n"
				+ "Parameters\n-a -- adds all the numbers together\n" +
				"-x -- multiplies all the numbers together\n";
	}

}
