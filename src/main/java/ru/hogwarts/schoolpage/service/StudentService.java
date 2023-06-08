package ru.hogwarts.schoolpage.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolpage.entity.Faculty;
import ru.hogwarts.schoolpage.entity.Student;
import ru.hogwarts.schoolpage.exception.StudentNotFoundException;
import ru.hogwarts.schoolpage.repository.FacultyRepository;
import ru.hogwarts.schoolpage.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student add(Student student) {
        student.setId(null);
        student.setFaculty(
                Optional.ofNullable(student.getFaculty())
                .filter(f -> f.getId() != null)
                .flatMap(f -> facultyRepository.findById(f.getId()))
                .orElse(null)
    );
        return studentRepository.save(student);
    }


    public Optional<Student> update(long id, Student newStudent) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(newStudent.getName());
                    oldStudent.setAge(newStudent.getAge());
                    oldStudent.setFaculty(
                            Optional.ofNullable(newStudent.getFaculty())
                                    .filter(f -> f.getId() != null)
                                    .flatMap(f -> facultyRepository.findById(f.getId()))
                                    .orElse(null)
                    );
                    return studentRepository.save(oldStudent);
                });
    }

    public Collection<Student> getALl() {
        return Collections.unmodifiableCollection(studentRepository.findAll());
    }

    public Optional<Student> deleteById(long id) {
     return studentRepository.findById(id)
             .map(student -> {
                 studentRepository.delete(student);
                 return student;
             });
    }


    public Optional<Student> getById(long id) {
        return studentRepository.findById(id);
    }

    public Collection<Student> getAllByAge(int age){
        return studentRepository.findAllByAge(age);}

    public Collection<Student> getAllByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public Optional<Faculty> getFacultyByStudentId(long id) {
        return studentRepository.findById(id)
                .map(Student::getFaculty);
    }

    public int getCountOfStudents() {
        return studentRepository.getCountOfStudents();
    }

    public double getAverageAgeOfStudents() {
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getLastStudents(int count) {
        return studentRepository.getLastStudents(count);
    }

    public Stream<String> findStudentsNamedWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted();
    }

    public double findStudentAverageAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow(StudentNotFoundException::new);
    }
}
