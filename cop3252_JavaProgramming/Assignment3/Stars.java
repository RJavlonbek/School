import javax.swing.JOptionPane;

public class Stars {

	private int rows;
	private int columns;
	private String symbol;
	private String Sky;

	public Stars() {
		rows = 1;
		columns = 1;
		symbol = "*"; // setting default star symbol to *
	}

	public Stars(int r, int c) {
		rows = r;
		columns = c;
	}

	public void setRows(int r)
	{
		rows = r;
	}

	public void setColumns(int c)
	{
		columns = c;
	}

	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
	}

	public void getUserInput()
	{
		setRows(Integer.parseInt(JOptionPane.showInputDialog(
				"How many rows of stars does our valiant knight see?", "10")));
			
		setColumns(Integer.parseInt(JOptionPane.showInputDialog(
				"How many columns of stars does he see?", "10")));
	}
	
	public void buildSky()
	{
		Sky = ""; // Reset Sky

		for (int j = 0; j < getRows(); j++)
		{
			if (j % 2 == 1)
				Sky = Sky + " ";

			for (int i = 0; i < getColumns(); i++)
			{
				Sky = Sky + symbol + " ";
			}// end columns for loop
			Sky = Sky + "\n";
		}// end rows for loop
	} // end buildSky

	// print stars in console
	public void printConsoleStars()
	{
		buildSky();
		System.out.print(Sky);
	}

	// builds and returns sky string. Used to pass to Knight.
	public String getSky()
	{
		buildSky();
		return Sky;
	}
}
