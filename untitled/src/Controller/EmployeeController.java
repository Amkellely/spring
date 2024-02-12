package src.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.Employee;
import src.DAO.EmployeeDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeDAO employeeDAO;

    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // Создание нового сотрудника
    public Employee createEmployee(Employee employee) {
        try {
            employee.setId(employeeDAO.getNextId());
            return employeeDAO.createEmployee(employee);
        } catch (Exception e) {
            logger.error("Error occurred while creating employee", e);
            throw e; // Рассмотрим перехват исключения и передачу его выше для обработки на уровне выше
        }
    }

    // Получение списка всех сотрудников
    public List<Employee> getAllEmployees() {
        try {
            return employeeDAO.getAllEmployees();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all employees", e);
            throw e;
        }
    }

    // Получение информации о конкретном сотруднике
    public Optional<Employee> getEmployeeById(long id) {
        try {
            return employeeDAO.getEmployeeById(id);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving employee by id: " + id, e);
            throw e;
        }
    }

    // Обновление информации о сотруднике
    public Optional<Employee> updateEmployee(long id, Employee updatedEmployee) {
        try {
            return employeeDAO.updateEmployee(id, updatedEmployee);
        } catch (Exception e) {
            logger.error("Error occurred while updating employee with id: " + id, e);
            throw e;
        }
    }

    // Удаление сотрудника
    public void deleteEmployee(long id) {
        try {
            employeeDAO.deleteEmployee(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting employee with id: " + id, e);
            throw e;
        }
    }

    // Поиск в памяти по имени
    public List<Employee> searchEmployeesByName(String name) {
        try {
            return employeeDAO.searchEmployeesByName(name);
        } catch (Exception e) {
            logger.error("Error occurred while searching employees by name: " + name, e);
            throw e;
        }
    }
}
