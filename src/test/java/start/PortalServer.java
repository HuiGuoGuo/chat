package start;
public class PortalServer {

	/**
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main( String[] args ) throws Exception {
		TomcatBootstrap.start( 80, Profiles.DEVELOPMENT );

	}

}