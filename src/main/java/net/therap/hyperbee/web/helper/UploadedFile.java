package net.therap.hyperbee.web.helper;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author azim
 * @since 11/22/16
 */

public class UploadedFile {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
