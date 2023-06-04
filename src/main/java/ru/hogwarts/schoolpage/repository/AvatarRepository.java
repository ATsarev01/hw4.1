package ru.hogwarts.schoolpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolpage.entity.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudent_Id(long studentId);

}
