package ru.hogwarts.schoolpage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schoolpage.entity.Avatar;
import ru.hogwarts.schoolpage.entity.Student;
import ru.hogwarts.schoolpage.exception.AvatarNotFoundException;
import ru.hogwarts.schoolpage.exception.AvatarProcessException;
import ru.hogwarts.schoolpage.exception.StudentNotFoundException;
import ru.hogwarts.schoolpage.repository.AvatarRepository;
import ru.hogwarts.schoolpage.repository.StudentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    private final String avatarDir;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository,
                         @Value("${application.avatar.store}")String avatarDir) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.avatarDir = avatarDir;
    }

    public void upload(long studentID, MultipartFile multipartFile) {
        try {
            Student student = studentRepository.findById(studentID)
                    .orElseThrow(StudentNotFoundException::new);
            String fileName = String.format(
                    "%d.%s",
                    student.getId(),
                    StringUtils.getFilenameExtension(multipartFile.getOriginalFilename())
                    );
            byte[] data = multipartFile.getBytes();
            Path path = Paths.get(avatarDir, fileName);
            Files.write(path, data);

            Avatar avatar = new Avatar();
            avatar.setData(data);
            avatar.setFilePath(path.toString());
            avatar.setFileSize(data.length);
            avatar.setStudent(student);
            avatar.setMediaType(multipartFile.getContentType());
            avatarRepository.save(avatar);

        } catch (IOException e){
            throw new AvatarProcessException();
        }



    }

    public Pair<byte[], String> downloadFromDb(long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);
        Avatar avatar = avatarRepository.findByStudent_Id(studentId).orElseThrow(AvatarNotFoundException::new);
        return Pair.of(avatar.getData(), avatar.getMediaType());
    }
    public Pair<byte[], String> downloadFromFs(long studentId) {
        try {
            Avatar avatar = avatarRepository.findByStudent_Id(studentId).orElseThrow
                    (AvatarNotFoundException::new);
            Path path = Paths.get(avatar.getFilePath());
            return Pair.of(Files.readAllBytes(path), avatar.getMediaType());
        } catch (IOException e) {
            throw  new AvatarProcessException();
        }
        }
    }
