package start;


import org.apache.catalina.startup.Tomcat;

/**
 *
 */
public class TomcatBootstrap {

	/**
	 * 启动.
	 */
	public static void start( int port, boolean isServlet3, String profile ) throws Exception {
		long beginTime = System.currentTimeMillis();

		// 设定Spring的profile.
		Profiles.setProfileAsSystemProperty( profile );
		
		//System.setProperty("spring.profiles.active", profile);

		Tomcat tomcat = TomcatFactory.createTomcat( port, isServlet3 );

		try {
			tomcat.start();

			long endTime = System.currentTimeMillis();

			System.err.println( "[INFO] Server running at http://localhost:" + port );
			System.err.println( "[INFO] Server startup in " + ( endTime - beginTime ) + "ms" );
			System.err.println( "[HINT] 回车快速重新启动应用程序" );

			// 等待用户输入回车重载应用.
			while ( true ) {
				char c = ( char ) System.in.read();
				if ( c == '\n' ) {
					beginTime = System.currentTimeMillis();

					// 重启.
					TomcatFactory.reloadContext( tomcat );

					endTime = System.currentTimeMillis();

					System.err.println( "[INFO] Server running at http://localhost:" + port );
					System.err.println( "[INFO] Server restart in " + ( endTime - beginTime ) + "ms" );
					System.err.println( "[HINT] 回车快速重新启动应用程序" );
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			System.exit( -1 );
		}
	}

	/**
	 * 启动.
	 */
	public static void start( int port, String profile ) throws Exception {
		start( port, Boolean.TRUE, profile );
	}

}