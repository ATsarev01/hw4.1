package ru.hogwarts.schoolpage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.schoolpage.dto.AvatarDto;
import ru.hogwarts.schoolpage.entity.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudent_Id(long studentId);
@Query("SELECT new ru.hogwarts.schoolpage.dto.AvatarDto(a.id, a.fileSize, a.mediaType, " +
        "'http://localhost:8080/avatar/download-from-db?studentId=' || a.student.id) from Avatar a")
    Page<AvatarDto> getPage(Pageable pageable);

}
