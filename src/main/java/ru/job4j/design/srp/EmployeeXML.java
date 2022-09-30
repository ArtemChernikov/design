package ru.job4j.design.srp;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Класс используется для поддержки отчетов в XML формате.
 *
 * @author ARTEM CHERNIKOV
 * @version 1.0
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXML {
    @XmlElement
    private List<Employee> employees;

    public EmployeeXML() {

    }

    public EmployeeXML(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
