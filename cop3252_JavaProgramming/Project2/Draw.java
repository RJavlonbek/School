import java.util.LinkedList;

public class Draw extends ActionBase {

	private static Draw _singletonInstance = new Draw();
	private Draw() {
		
	}
	
	
	public static Draw getInstance() {
		return _singletonInstance;
	}
	
	
	/* Example Inputs:
	 *  'draw'
	 *  'draw -b' 
	 *  'draw -d'
	 *  'draw -m'
	 */ 
	
	
	@Override
	public void action(LinkedList<String> list) {
		if (!list.isEmpty() && list.peekFirst().startsWith("-")) {
			String parm = list.removeFirst();

			switch (parm.charAt(1)) {
			case 'b':
				
				for(int i=0;i<13;i++)
					System.out.print("#");
				System.out.println();
				for(int i=0;i<6;i++)
					System.out.println("#           #");
				for(int i=0;i<13;i++)
					System.out.print("#");
				System.out.println();
				break;
				
			case 'd':
					for (int i = 1; i < 10; i += 2) {
						for (int j = 0; j < 9 - i / 2; j++)
							System.out.print(" ");

						for (int j = 0; j < i; j++)
							System.out.print("*");

						System.out.println();
					}

					for (int i = 7; i > 0; i -= 2) {
						for (int j = 0; j < 9 - i / 2; j++)
							System.out.print(" ");

						for (int j = 0; j < i; j++)
							System.out.print("*");

						System.out.println();
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
		}else {
			System.out.println("##    ###     #    #           #");
			System.out.println("# #   #  #   # #    #         # ");
			System.out.println("#  #  ###   #####    #   #   #  ");
			System.out.println("# #   #  #  #   #     # # # #   ");
			System.out.println("##    #  #  #   #      #   #    ");
		}

	}

	@Override
	public String manual() {
		return "This command draws a shape.\n" +
				"Parameters\n" +
				"-b -- draws a box out of #\n" +
				"-d -- draws a diamond out of * " +
				"no command prints out DRAW in large letters\n";
	}

}
