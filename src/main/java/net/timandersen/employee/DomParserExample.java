package net.timandersen.employee;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DomParserExample {

    private List<Employee> employees;
    private Document dom;

    public DomParserExample() {
        employees = new ArrayList<Employee>();
    }

    public void runExample() {
        parseXmlFile();
        parseDocument();
        printData();
    }

    private void parseXmlFile() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("employees.xml");
            dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
            if (inputStream != null) inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseDocument() {
        Element documentElement = dom.getDocumentElement();
        NodeList nodeList = documentElement.getElementsByTagName("Employee");
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                employees.add(getEmployee(element));
            }
        }
    }

    private Employee getEmployee(Element element) {
        String name = getTextValue(element, "Name");
        int id = getIntValue(element, "Id");
        int age = getIntValue(element, "Age");
        String type = element.getAttribute("type");
        return new Employee(name, id, age, type);
    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }

    private int getIntValue(Element ele, String tagName) {
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    private void printData() {
        System.out.println("No of Employees '" + employees.size() + "'.");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
