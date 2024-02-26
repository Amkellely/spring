package src.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    private OfficeRepository officeRepository;

    // Создание офиса
    @PostMapping
    public Office createOffice(@RequestBody Office office) {
        return officeRepository.save(office);
    }

    // Получение всех офисов
    @GetMapping
    public Iterable<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    // Получение офиса по идентификатору
    @GetMapping("/{id}")
    public Office getOfficeById(@PathVariable Long id) {
        return officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));
    }

    // Обновление офиса
    @PutMapping("/{id}")
    public Office updateOffice(@PathVariable Long id, @RequestBody Office officeDetails) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));

        office.setName(officeDetails.getName());
        office.setAddress(officeDetails.getAddress());

        return officeRepository.save(office);
    }

    // Удаление офиса
    @DeleteMapping("/{id}")
    public void deleteOffice(@PathVariable Long id) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));

        officeRepository.delete(office);
    }
}
