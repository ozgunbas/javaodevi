import java.text.DecimalFormat;

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
				Place maxp = ns.to;
				double max = maxp.getUtility();
				Action act = t.getAct();
				for(int i = 0; i < 2; i++)
				{
				
					t = t.next();
					ns = t.getNext();
					if(max < ns.to.getUtility())
					{
						max = ns.to.getUtility();
						maxp = ns.to;
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

}
