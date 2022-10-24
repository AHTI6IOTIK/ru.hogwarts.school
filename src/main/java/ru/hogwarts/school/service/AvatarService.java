package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.UnsupportedMediaType;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    private static final int SIZE_LIMIT = 1048576;

    @Value("${path.to.avatars.folder}")
    private String uploadDir;

    private final Logger logger;

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.logger = LoggerFactory.getLogger(AvatarService.class);
    }

    public void upload(Long studentId, MultipartFile uploadedAvatar) throws IOException {
        logger.info("Was invoked method for avatar upload");
        Student student = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
        Path filePath = Path.of(
            uploadDir,
            student.getId() + "." + getExtension(uploadedAvatar.getContentType())
        );

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
            InputStream fromIS = uploadedAvatar.getInputStream();
            OutputStream toIS = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bufFromIS = new BufferedInputStream(fromIS, 1024);
            BufferedOutputStream bufToIS = new BufferedOutputStream(toIS, 1024)
        ) {
            bufFromIS.transferTo(bufToIS);
        }

        Avatar avatar = findAvatarByStudentId(student.getId())
            .setFilePath(filePath.toString())
            .setFileSize(uploadedAvatar.getSize())
            .setData(uploadedAvatar.getBytes())
            .setMediaType(uploadedAvatar.getContentType())
        ;

        student.setAvatar(avatar);

        avatarRepository.save(avatar);
        studentRepository.save(student);
    }

    public Avatar findAvatarByStudentId(Long id) {
        logger.info("Was invoked method for avatar findAvatarByStudentId");
        Avatar avatar = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new).getAvatar();
        return null != avatar ? avatar : new Avatar();
    }

    private String getExtension(String contentType) {
        logger.info("Was invoked method for avatar getExtension");
        switch (contentType) {
            case MediaType.IMAGE_GIF_VALUE:
                return "gif";
            case MediaType.IMAGE_JPEG_VALUE:
                return "jpeg";
            case MediaType.IMAGE_PNG_VALUE:
                return "png";
        }

        throw new UnsupportedMediaType();
    }

    public boolean isCorrectFileSize(long size) {
        logger.info("Was invoked method for avatar isCorrectFileSize");
        return size > SIZE_LIMIT;
    }

    public Page<Avatar> getAvatars(int pageNum, int pageSize) {
        logger.info("Was invoked method for avatar getAvatars");
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return avatarRepository.findAll(pageRequest);
    }
}
