package net.timandersen.employee;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SAXParserExample {

    private List<Employee> employees;
    private SaxHandler delegate;

    public SAXParserExample() {
        employees = new ArrayList<Employee>();
        delegate = new SaxHandler();
    }

    public void runExample() {
        parseDocument();
        printData();
    }

    private void parseDocument() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("employees.xml");
            SAXParserFactory.newInstance().newSAXParser().parse(inputStream, delegate);
            if (inputStream != null) inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printData() {
        System.out.println("No of Employees '" + employees.size() + "'.");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public List<Employee> getEmployees() {
        return new ArrayList<Employee>(employees);
    }

    private class SaxHandler extends DefaultHandler {

        private String value;
        private Employee employee;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //reset
            value = "";
            if ("Employee".equalsIgnoreCase(qName)) {
                //create a new instance of employee
                employee = new Employee();
                employee.setType(attributes.getValue("type"));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            value = new String(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("Employee".equalsIgnoreCase(qName)) employees.add(employee);
            if ("Name".equalsIgnoreCase(qName)) employee.setName(value);
            if ("Id".equalsIgnoreCase(qName)) employee.setEmployeeNumber(Integer.parseInt(value));
            if ("Age".equalsIgnoreCase(qName)) employee.setAge(Integer.parseInt(value));
        }

    }

}




