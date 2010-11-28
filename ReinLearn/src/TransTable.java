import java.util.Hashtable;



public class TransTable extends Table {
	private Hashtable<Integer,Trans> tl;
	public TransTable() {
		super("rand_transitions.txt");
		
	}
	class NewSt
	{
		Place to;
		double prob;
		NewSt ns = null;
		public NewSt(Place to, double prob) {
			super();
			this.to = to;
			this.prob = prob;
		}
		
	}
	class Trans
	{
		private Place from;
		private Action act;
		private NewSt next = null;
		private Trans nextt = null;
		public Trans(Place from, Action act) {
			super();
			this.from = from;
			this.act = act;
		}
		public NewSt getNext() {
			return next;
		}
		public Trans next()
		{
			return nextt;
		}
		public void setNext(Trans t)
		{
			nextt = t;
		}
		public void addNext(NewSt next) {
			if(this.next==null)
				this.next = next;
			else
			{
				NewSt curr = this.next;
				while(curr.ns != null)
					curr = curr.ns;
				curr.ns = next;
			}
		}
		public Place getFrom() {
			return from;
		}
		public Action getAct() {
			return act;
		}
		
	}
	@Override
	protected void addToList(String nextLine) {
		String[] linesp = nextLine.split(",");
		Trans read = new Trans(PlaceTable.numToPlace(Integer.parseInt(linesp[0])), translateAct(linesp[1]));
		NewSt fnext = new NewSt(PlaceTable.numToPlace(Integer.parseInt(linesp[2])), Double.parseDouble(linesp[3]));
		read.addNext(fnext);
		if(linesp.length > 4)
		{
			fnext.ns = new NewSt(PlaceTable.numToPlace(Integer.parseInt(linesp[4])), Double.parseDouble(linesp[5]));
			fnext.ns.ns = new NewSt(PlaceTable.numToPlace(Integer.parseInt(linesp[6])), Double.parseDouble(linesp[7]));
		}
		Trans head = tl.get(read.getFrom().getStatenumber());
		if(head == null)
			tl.put(read.getFrom().getStatenumber(), read);
		else
		{
			Trans curr = head;
			while(curr.nextt != null)
				curr = curr.nextt;
			curr.nextt = read;
			tl.put(head.getFrom().getStatenumber(), head);
		}
	}

	private Action translateAct(String string) {
		if(string.toLowerCase().equals("s"))
			return Action.SCARLET;
		else if(string.toLowerCase().equals("g"))
			return Action.GRAY;
		else if(string.toLowerCase().equals("b"))
			return Action.BLACK;
		else
			return null;
	}

	@Override
	protected void initdatastruct() {
		tl = new Hashtable<Integer, Trans>();
		
	}

	public Trans getTrans(int sourceNum)
	{
		return tl.get(sourceNum);
	}

}
