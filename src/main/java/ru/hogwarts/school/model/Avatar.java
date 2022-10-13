package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    public Long getId() {
        return id;
    }

    public Avatar setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Avatar setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Avatar setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Avatar setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public Avatar setData(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avatar)) return false;
        Avatar avatar = (Avatar) o;
        return fileSize == avatar.fileSize && id.equals(avatar.id) && filePath.equals(avatar.filePath) && mediaType.equals(avatar.mediaType) && Arrays.equals(data, avatar.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
            "id=" + id +
            ", filePath='" + filePath + '\'' +
            ", fileSize=" + fileSize +
            ", mediaType='" + mediaType + '\'' +
            ", data=" + Arrays.toString(data) +
            '}';
    }
}
