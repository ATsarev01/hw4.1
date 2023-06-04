package ru.hogwarts.schoolpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolpage.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findAllByColor(String color);
    List<Faculty> findAllByNameContainsIgnoreCaseOrColorContainsIgnoreCase(String name, String color);

}
