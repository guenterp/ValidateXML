/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author gP
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateTest {

    public ValidateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of validateXMLSchema method, of class Validate. 
     * only first error is displayed
     */
    @Test
    public void testValidateXMLSchema() {
        System.out.format("%n%s%n", "testing validateXMLSchema");

        boolean expResult, result;
        String xsdPath, xmlPath;

        // the "smallest" fundsXML file validated against official fundsXML schema file
        // (see also http://fundsxml.org/)
        xsdPath = "FundsXML3.2.xsd";
        xmlPath = "helloFundsXML.xml";
        expResult = true;
        result = Validate.validateXMLSchema(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // 2 errors: wrong date, no language set
        // only first error found is displayed
        xsdPath = "FundsXML3.2.xsd";
        xmlPath = "helloFundsXML_2.xml";
        expResult = false;
        result = Validate.validateXMLSchema(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // validate request against Employee schema (id only, must be an integer value)
        xsdPath = "Employee.xsd";
        xmlPath = "EmployeeRequest.xml";
        expResult = true;
        result = Validate.validateXMLSchema(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // validate response (id, role, fullname) against Employee schema
        // id must be integer, role and fullname must be nonempty strings
        xsdPath = "Employee.xsd";
        xmlPath = "EmployeeResponse.xml";
        expResult = true;
        result = Validate.validateXMLSchema(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // 2 errors: role has white space only (blank, tab), fullname is empty
        // only first error found is displayed 
        xsdPath = "Employee.xsd";
        xmlPath = "EmployeeResponse_2.xml";
        expResult = false;
        result = Validate.validateXMLSchema(xsdPath, xmlPath);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateXMLSchemaAllErrors method, of class Validate.
     * all warnings or errors are displayed
     */
    @Test
    public void testValidateXMLSchemaAllErrors() {
        System.out.format("%n%s%n", "testing validateXMLSchemaAllErrors");

        boolean expResult, result;
        String xsdPath, xmlPath;

        // the "smallest" fundsXML file validated against official fundsXML schema file
        // (see also http://fundsxml.org/)
        xsdPath = "FundsXML3.2.xsd";
        xmlPath = "helloFundsXML.xml";
        expResult = true;
        result = Validate.validateXMLSchemaAllErrors(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // 2 errors: wrong date, no language set
        // both errors found are displayed
        xsdPath = "FundsXML3.2.xsd";
        xmlPath = "helloFundsXML_2.xml";
        expResult = false;
        result = Validate.validateXMLSchemaAllErrors(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // validate request against Employee schema (id only, must be an integer value)
        xsdPath = "Employee.xsd";
        xmlPath = "EmployeeRequest.xml";
        expResult = true;
        result = Validate.validateXMLSchemaAllErrors(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // validate response (id, role, fullname) against Employee schema
        // id must be integer, role and fullname must be nonempty strings
        xsdPath = "Employee.xsd";
        xmlPath = "EmployeeResponse.xml";
        expResult = true;
        result = Validate.validateXMLSchemaAllErrors(xsdPath, xmlPath);
        assertEquals(expResult, result);

        // 2 errors: role has white space only (blank, tab), fullname is empty
        // both errors found are displayed
        xsdPath = "Employee.xsd";
        xmlPath = "EmployeeResponse_2.xml";
        expResult = false;
        result = Validate.validateXMLSchemaAllErrors(xsdPath, xmlPath);
        assertEquals(expResult, result);
    }
}
