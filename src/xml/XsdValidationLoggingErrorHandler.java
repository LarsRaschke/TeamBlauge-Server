//Tingar und Wohlrab

package xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * Errorhandler
 * @author uidp2242
 *
 */
public class XsdValidationLoggingErrorHandler implements ErrorHandler
{
	/**
	 * Warning Handler
	 */
	public void warning(SAXParseException ex) throws SAXException {
		System.out.println("Warnung: " + ex.getMessage());
	}
	/**
	 * error Handler
	 */
	public void error(SAXParseException ex) throws SAXException {
		System.out.println("Fehler: " + ex.getMessage());
	}
	/**
	 * fatal Error Handler
	 */
	public void fatalError(SAXParseException ex) throws SAXException {
		System.out.println("Fataler Fehler: " + ex.getMessage());
	}
}
