import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


public class PlaceTable extends Table {
	private static ArrayList<Place> al;
	private static boolean updated;
	public static void resetUpdated()
	{
		updated = false;
	}
	public static void setUpdated()
	{
		updated = true;
	}
	public static boolean isUpdated() {
		return updated;
	}


	public PlaceTable() {
		super("place_descriptions.txt");
	}

	
	@Override
	protected void addToList(String nextLine) {
		String[] linesp = nextLine.split(",");
		Place p;
		if (linesp.length == 4)
			p = new Place(Integer.parseInt(linesp[0]), linesp[1], linesp[2],
					Double.parseDouble(linesp[3]));
		else
			p = new Place(Integer.parseInt(linesp[0]), linesp[1], linesp[2],
					Double.parseDouble(linesp[3]), linesp[4]);
		if(p.getStatenumber() != (al.size()+1))
		{
			System.out.println("ERROR: state number. Line number and state number must match!");
		}
		else
			al.add(p);

	}

	@Override
	protected void initdatastruct() {
		al = new ArrayList<Place>();
		updated = true;
	}
	public static Place numToPlace(int stateNum)
	{
		return al.get(stateNum-1);
	}
	
	public static int tableLength()
	{
		return al.size();
	}
	public Place random() {
		return al.get(((new Random()).nextInt(al.size())));
	}
	public static boolean hasUnvisitedState() {
		for (Place p : al) {
			if(!p.isVisted())
				return true;
		}
		return false;
	}
	public static void printTable() {
		DecimalFormat df = new DecimalFormat("#.##");
		for (Place p : al) {
			 System.out.println("State " + p.getStatenumber() + ": Utility: " + df.format(p.getUtility()) +" Policy:" + p.getPolicy());
		}
			
		
	}

}
