public class ThreadtownCity {

	public static void main(String[] args) {
		Parkhaus ph = new Parkhaus(10);
		for(int i=1; i<=20; i++)
			new Auto( String.format("Auto-%2d", i), ph );
	}

}
