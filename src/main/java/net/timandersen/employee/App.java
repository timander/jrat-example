package net.timandersen.employee;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;


public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/application-context.xml");
        EmployeeRepository employeeRepository = (EmployeeRepository) context.getBean("employeeRepository");

        DomParserExample domParser = (DomParserExample) context.getBean("domParser");
        domParser.runExample();
        List<Employee> domEmployees = domParser.getEmployees();
        employeeRepository.saveOrUpdateAll(domEmployees);

        SAXParserExample saxParser = (SAXParserExample) context.getBean("saxParser");
        saxParser.runExample();
        List<Employee> saxEmployees = saxParser.getEmployees();
        employeeRepository.saveOrUpdateAll(saxEmployees);

        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        int countOfEmployees = jdbcTemplate.queryForInt("select count(*) from Employees");
        Assert.assertEquals((long) (domEmployees.size() + saxEmployees.size()), (long) countOfEmployees);
    }

}
