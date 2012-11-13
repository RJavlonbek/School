import java.util.LinkedList;

public class Echo extends ActionBase {

	private static Echo _singletonInstance = new Echo();
	private Echo() {
		
	}
	
	
	public static Echo getInstance() {
		return _singletonInstance;
	}
	
	
	@Override
	public void action(LinkedList<String> list) {

		//try{
		if (!list.isEmpty() && list.peekFirst().startsWith("-")) {

			String parm = list.removeFirst();
			iter = list.iterator();

			switch (parm.charAt(1)) {
			case 'u':
				while (iter.hasNext()) {
					System.out.print((iter.next().toUpperCase() + " "));
				}
				System.out.println();
				break;
			case 'l':
				while (iter.hasNext()) {
					System.out.print((iter.next().toLowerCase() + " "));
				}
				System.out.println();
				break;
			case 'm':
				System.out.println(this.manual());
				break;
			default:
				System.out.println("Unknown parameter...see man page");
				break;
			}

		} else {
			iter = list.iterator();
			while (iter.hasNext()) {
				System.out.print(iter.next()+ " ");
			}
			System.out.println();
		}
		//Dont need anymore, works without
		//}catch (NullPointerException ex) {
		//	System.out.println("Null Pointer Error, please follow echo with a keyword or type 'echo -m' for help ");
		//}
	}

	@Override
	public String manual() {
		return "This command prints the typed text following the keyword echo.\n" +
				"Parameters\n-u -- prints string in all uppercase\n-l -- prints " +
				"string in all lowercase\n";
	}

}
