import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

public class Dice extends ActionBase {

	private static Dice _singletonInstance = new Dice();
	
	private int diceSides = 6;
	Random r = new Random();
	private Dice() {
		
	}
	
	
	public static Dice getInstance() {
		return _singletonInstance;
	}
	
	/* Example Inputs:
	 *  'dice'
	 *  'dice -s' 
	 *  'dice -s 12'
	 *  'dice -d'
	 *  'dice -d 3'
	 *  'dice -m'
	 */ 
	
	@Override
	public void action(LinkedList<String> list) {

		if (!list.isEmpty() && list.peekFirst().startsWith("-")) {

			String parm = list.removeFirst();
			iter = list.iterator();

			switch (parm.charAt(1)) {
			case 's': // size
				try {
					diceSides = Integer.parseInt(iter.next());
				}catch (NumberFormatException ex) {
					System.out.println("Input error, rolling D6 dice");
				}catch(NoSuchElementException ex) {
					diceSides = 6;
				}
				System.out.println("D" + diceSides + ": "+ (r.nextInt(diceSides)+1));
				break;
			case 'd': // double dice roll
				try {
					diceSides = Integer.parseInt(iter.next());
				}catch (NumberFormatException ex) {
					System.out.println("Input error, rolling 2x D6 dice");
				}catch(NoSuchElementException ex) {
					diceSides = 6;
				}
				System.out.println("D" + diceSides + ": "+ (r.nextInt(diceSides)+1));
				System.out.println("D" + diceSides + ": "+ (r.nextInt(diceSides)+1));
				break;
			case 'm':
				System.out.println(this.manual());
				break;
			default:
				System.out.println("Unknown parameter...see man page");
				break;
			}

		} else {
			System.out.println("D" + diceSides + ": " + (r.nextInt(diceSides)+1));
		}
	}

	@Override
	public String manual() {
		return "This command rolls a dice\n" +
				"Parameters\n" +
				"-s -- rolls a dice using the integer following s i.e 'dice -s 10'\n" +
				"-d -- rolls double dice using the integer following d, i.e. 'dice -d 12'\n";
	}

}
