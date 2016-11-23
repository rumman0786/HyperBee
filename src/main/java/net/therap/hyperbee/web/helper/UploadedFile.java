package net.therap.hyperbee.web.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * @author azim
 * @since 11/22/16
 */
@Component
public class UploadedFile {

    @Autowired
    ServletContext context;

    public String uploadFile(CommonsMultipartFile fileUpload, String fileName) {

        String filename = fileName + "." + fileUpload.getOriginalFilename();

        try

        {
            byte barr[] = fileUpload.getBytes();
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(System.getProperty("catalina.home") + "/webapps/upload/" + filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (
                Exception e
                )
        {
            System.out.println(e);
        }
    return filename;
    }
}
