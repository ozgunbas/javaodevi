import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public abstract class Table {

	public Table(String filename) {
		initdatastruct();
		File file = new File(filename);
		
		Scanner scan;
		try {
			scan = new Scanner(new FileReader(file));
			while(scan.hasNextLine())
			{
				addToList(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected abstract void addToList(String nextLine);
	protected abstract void initdatastruct();
}
