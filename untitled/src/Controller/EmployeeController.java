package src.Controller;

import src.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    // Создание нового сотрудника
    public Employee createEmployee(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    // Получение списка всех сотрудников
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Получение информации о конкретном сотруднике
    public Optional<Employee> getEmployeeById(long id) {
        return employees.stream().filter(e -> e.getId() == id).findFirst();
    }

    // Обновление информации о сотруднике
    public Optional<Employee> updateEmployee(long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = getEmployeeById(id);
        existingEmployee.ifPresent(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setPosition(updatedEmployee.getPosition());
        });
        return existingEmployee;
    }

    // Удаление сотрудника
    public void deleteEmployee(long id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    // Поиск в памяти по имени
    public List<Employee> searchEmployeesByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}