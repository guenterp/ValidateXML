/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

/**
 *
 * @author gP
 */
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Validate {

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            System.out.println("    xml: " + xmlPath + ", xsd: " + xsdPath);

            // create schema factory
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // create schema object from factory
            Schema schema = factory.newSchema(new File(xsdPath));
            // create validator object from schema
            Validator validator = schema.newValidator();
            // validate XML
            validator.validate(new StreamSource(new File(xmlPath)));

        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        
        // no exception occurred => validation was ok
        return true;
    }

    public static boolean validateXMLSchemaAllErrors(String xsdPath, String xmlPath) {
        try {
            System.out.println("    xml: " + xmlPath + ", xsd: " + xsdPath);

            // create schema factory
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // create schema object from factory
            Schema schema = factory.newSchema(new File(xsdPath));
            // create validator object from schema
            Validator validator = schema.newValidator();

            // if an exception occurs then add it to the list
            final List<SAXParseException> exceptions = new LinkedList<>();
            validator.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }
            });

            // validate XML
            validator.validate(new StreamSource(new File(xmlPath)));

            // print all exceptions occured
            for (Exception e : exceptions) {
                System.out.println(e);
            }

            if (!exceptions.isEmpty()) {
                System.out.println("an exception occured");
                return (false);
            }

        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        
         // no exception occurred => validation was ok
        return true;
    }
}
