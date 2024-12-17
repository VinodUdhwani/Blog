package com.blog.Blog.payLoads;

public class FileResponse {

    private String fileName;
    private String message;
    private boolean status;

    public FileResponse(String fileName, String message, boolean status) {
        this.fileName = fileName;
        this.message = message;
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
