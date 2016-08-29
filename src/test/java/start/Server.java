package start;
public class Server {

	/**
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main( String[] args ) throws Exception {
		TomcatBootstrap.start( 80, Profiles.DEVELOPMENT);

	}

}