import java.util.logging.Logger;

public class TestAuth{
	private static final Logger log = Logger.getLogger(TestAuth.class.getName());
public static void main(String [ ] args)
{


	
	String hashedPassword = org.jboss.security.auth.spi.Util.createPasswordHash("MD5", "BASE64", null, null, "userbob");
	log.info(String.format("hashed password: %s", hashedPassword));
}
}