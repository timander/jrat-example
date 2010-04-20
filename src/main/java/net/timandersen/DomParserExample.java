package net.timandersen;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DomParserExample {

    private List<Employee> employees;
    private Document dom;

    public DomParserExample() {
        //create a list to hold the employee objects
        employees = new ArrayList<Employee>();
    }


    public static void main(String[] args) {
        //create an instance
        DomParserExample dpe = new DomParserExample();

        //call run example
        dpe.runExample();
    }

    public void runExample() {

        //parse the xml file and get the dom object
        parseXmlFile();

        //get each employee element and create a Employee object
        parseDocument();

        //Iterate through the list and print the data
        printData();

    }


    private void parseXmlFile() {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        InputStream inputStream = null;
        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            inputStream = getClass().getClassLoader().getResourceAsStream("employees.xml");
            dom = db.parse(inputStream);

        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (SAXException se) {
            se.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
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


    private void parseDocument() {
        //get the root elememt
        Element docEle = dom.getDocumentElement();

        //get a nodelist of <employee> elements
        NodeList nl = docEle.getElementsByTagName("Employee");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {

                //get the employee element
                Element el = (Element) nl.item(i);

                //get the Employee object
                Employee e = getEmployee(el);

                //add it to list
                employees.add(e);
            }
        }
    }


    /** I take an employee element and read the values in, create an Employee object and return it */
    private Employee getEmployee(Element empEl) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        String name = getTextValue(empEl, "Name");
        int id = getIntValue(empEl, "Id");
        int age = getIntValue(empEl, "Age");

        String type = empEl.getAttribute("type");

        //Create a new Employee with the value read from the xml nodes
        Employee e = new Employee(name, id, age, type);

        return e;
    }


    /**
     * I take a xml element and the tag name, look for the tag and get the text content i.e for
     * <employee><name>John</name></employee> xml snippet if the Element points to employee node and tagName is name I will return
     * John
     */
    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }


    /** Calls getTextValue and returns a int value */
    private int getIntValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    /** Iterate through the list and print the content to console */
    private void printData() {

        System.out.println("No of Employees '" + employees.size() + "'.");

        Iterator it = employees.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }


    public List<Employee> getEmployees() {
        return employees;
    }

}
