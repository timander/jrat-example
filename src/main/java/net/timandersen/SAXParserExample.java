package net.timandersen;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SAXParserExample extends DefaultHandler {

    private List<Employee> employees;

    private String tempVal;

    //to maintain context

    private Employee tempEmp;

    public SAXParserExample() {
        employees = new ArrayList<Employee>();
    }


    public static void main(String[] args) {
        SAXParserExample spe = new SAXParserExample();
        spe.runExample();
    }

    public void runExample() {
        parseDocument();
        printData();
    }

    private void parseDocument() {

        //get a factory
        SAXParserFactory spf = SAXParserFactory.newInstance();
        InputStream inputStream = null;

        try {

            //get a new instance of parser
            SAXParser sp = spf.newSAXParser();

            //parse the file and also register this class for call backs
            inputStream = getClass().getClassLoader().getResourceAsStream("employees.xml");
            sp.parse(inputStream, this);

        }
        catch (SAXException se) {
            se.printStackTrace();
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /** Iterate through the list and print the contents */
    private void printData() {

        System.out.println("No of Employees '" + employees.size() + "'.");

        Iterator it = employees.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }


    //Event Handlers
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //reset
        tempVal = "";
        if (qName.equalsIgnoreCase("Employee")) {
            //create a new instance of employee
            tempEmp = new Employee();
            tempEmp.setType(attributes.getValue("type"));
        }
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ("Employee".equalsIgnoreCase(qName)) {
            //add it to the list
            employees.add(tempEmp);

        }
        else if ("Name".equalsIgnoreCase(qName)) {
            tempEmp.setName(tempVal);
        }
        else if ("Id".equalsIgnoreCase(qName)) {
            tempEmp.setEmployeeNumber(Integer.parseInt(tempVal));
        }
        else if ("Age".equalsIgnoreCase(qName)) {
            tempEmp.setAge(Integer.parseInt(tempVal));
        }

    }

    public List<Employee> getEmployees() {
        return new ArrayList<Employee>(employees);
    }

}




