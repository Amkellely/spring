package src;

import src.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    long getNextId();
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Optional<Employee> updateEmployee(long id, Employee updatedEmployee);
    void deleteEmployee(long id);
    List<Employee> searchEmployeesByName(String name);
}
java
        Copy code
        package src.DAO;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import src.Employee;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
    private List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    @Override
    public long getNextId() {
        return nextId++;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        try {
            employee.setId(getNextId());
            employees.add(employee);
            return employee;
        } catch (Exception e) {
            logger.error("Error occurred while creating employee", e);
            throw e;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return employees;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all employees", e);
            throw e;
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        try {
            return employees.stream().filter(e -> e.getId() == id).findFirst();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving employee by id: " + id, e);
            throw e;
        }
    }

    @Override
    public Optional<Employee> updateEmployee(long id, Employee updatedEmployee) {
        try {
            Optional<Employee> existingEmployee = getEmployeeById(id);
            existingEmployee.ifPresent(employee -> {
                employee.setName(updatedEmployee.getName());
                employee.setPosition(updatedEmployee.getPosition());
            });
            return existingEmployee;
        } catch (Exception e) {
            logger.error("Error occurred while updating employee with id: " + id, e);
            throw e;
        }
    }

    @Override
    public void deleteEmployee(long id) {
        try {
            employees.removeIf(employee -> employee.getId() == id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting employee with id: " + id, e);
            throw e;
        }
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        try {
            return employees.stream()
                    .filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        } catch (Exception e) {
            logger.error("Error occurred while searching employees by name: " + name, e);
            throw e;
        }
    }
}