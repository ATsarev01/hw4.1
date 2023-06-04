package ru.hogwarts.schoolpage.dto;

public class AvatarDto {

    private long id;
    private long fileSize;
    private String mediaType;
    private String url;

    public AvatarDto(long id, long fileSize, String mediaType, String url) {
        this.id = id;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.url = url;
    }

    public AvatarDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
