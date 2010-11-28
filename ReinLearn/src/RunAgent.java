import java.text.DecimalFormat;
import java.util.Random;


//Author:Erdeniz Bas
public class RunAgent {


	public static void main(String[] args) {
		
		PlaceTable pt =  new PlaceTable();
		TransTable st = new TransTable();
        DecimalFormat df = new DecimalFormat("#.##");
		while(PlaceTable.isUpdated() || PlaceTable.hasUnvisitedState())
		{
			System.out.println("Random Restart");
			System.out.println("--------------");
			Place p = pt.random();
			p.setVisted(true);
			while((p.getReward() != 1) && (p.getReward() != -1))
			{
				PlaceTable.resetUpdated();
				System.out.print(p.toString());
				TransTable.Trans t = st.getTrans(p.getStatenumber());
				TransTable.NewSt ns = t.getNext();
				Place maxp = goRand(ns);
				double max = calcAddUtil(ns); 
				Action act = t.getAct();
				for(int i = 0; i < 2; i++)
				{
				
					t = t.next();
					ns = t.getNext();
					double calced = calcAddUtil(ns);
					if(max < calced)
					{
						max = calced;
						maxp = goRand(ns);
						act = t.getAct();
					}
				}
				p.setUtility(max + p.getReward());
				p.setPolicy(act);
				System.out.print(" Best:" + act + " utility(" + p.getStatenumber() + "):"+ df.format(p.getUtility())+"\n");
				p = maxp;
			}
			p.setUtility(p.getReward());
			System.out.println(p.toString());
			
			
		}
		System.out.println("RESULTS");
		System.out.println("-------");
		PlaceTable.printTable(); 
	}

	private static double calcAddUtil(TransTable.NewSt ns) {
		double max = ns.to.getUtility() * ns.prob;
		if(ns.ns != null)
		{
			max += ns.ns.prob * ns.ns.to.getUtility();
			max += ns.ns.ns.prob * ns.ns.ns.to.getUtility();
		}
		return max;
	}

	private static Place goRand(TransTable.NewSt ns) {
		if(ns.ns == null)
			return ns.to;
		double x = new Random().nextDouble();
		if(x<ns.prob)
			return ns.to;
		else if(x<(ns.prob + ns.ns.prob))
			return ns.ns.to;
		else
			return ns.ns.ns.to;
	}

}
