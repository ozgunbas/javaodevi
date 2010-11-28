class Place {
	private int statenumber;
	private String placeName;
	private String imageFile;
	private double reward;
	private String comment = "";
	private double utility = 0;
	private Action policy = Action.NONE;
	private boolean visted = false;

	public Place(int statenumber, String placeName, String imageFile,
			double reward, String comment) {
		super();
		this.statenumber = statenumber;
		this.placeName = placeName;
		this.imageFile = imageFile;
		this.reward = reward;
		this.comment = comment;
	}

	public Place(int statenumber, String placeName, String imageFile,
			double reward) {
		super();
		this.statenumber = statenumber;
		this.placeName = placeName;
		this.imageFile = imageFile;
		this.reward = reward;
	}

	public int getStatenumber() {
		return statenumber;
	}

	public String getPlaceName() {
		return placeName;
	}

	public String getImageFile() {
		return imageFile;
	}

	public double getReward() {
		return reward;
	}

	public String getComment() {
		return comment;
	}
	@Override
	public String toString() {
	
		return "State: " + statenumber + "-"+placeName+" Reward: " + reward;
	}

	public void setUtility(double utility) {
		if(utility != this.utility)
		{
			PlaceTable.setUpdated();
			this.visted = false;
			this.utility = utility;
		}
	}

	public double getUtility() {
		return utility;
	}

	public void setPolicy(Action policy) {
		this.policy = policy;
	}

	public Action getPolicy() {
		return policy;
	}

	public void setVisted(boolean visted) {
		this.visted = visted;
	}

	public boolean isVisted() {
		return visted;
	}
}
