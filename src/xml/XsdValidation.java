package xml;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.*;

public class XsdValidation 
{
	public static void validateProjectlist() throws SAXException, IOException, FileNotFoundException 
	{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try 
		{
			Schema schema = schemaFactory.newSchema(new File("src/xml/schemas/projectlist_schema.xsd"));
			Validator validator = schema.newValidator();
			validator.setErrorHandler(new XsdValidationLoggingErrorHandler());
			validator.validate(new StreamSource(new File("src/xml/files/_projectlist.xml")));
		} catch (Exception e)
		{
			System.out.println("Fehler: " + e.getMessage());
		}
	}
		public static void validateProjects(int id, String xmlFilename) throws SAXException, IOException, FileNotFoundException 
		{
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			try 
			{
				Schema schema = schemaFactory.newSchema(new File("src/xml/schemas/projects_schema.xsd"));
				Validator validator = schema.newValidator();
				validator.setErrorHandler(new XsdValidationLoggingErrorHandler());
				validator.validate(new StreamSource(new File("src/xml/files/" + id + "_" + xmlFilename + ".xml")));
			} catch (Exception e) 
			{
				System.out.println("Fehler: " + e.getMessage());
			}
	}
}