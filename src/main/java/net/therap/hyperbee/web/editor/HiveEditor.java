package net.therap.hyperbee.web.editor;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

/**
 * @author rumman
 * @since 12/8/16
 */
public class HiveEditor extends PropertyEditorSupport {

    @Autowired
    private HiveService hiveService;

    @Override
    public void setAsText(String text) {
        Hive hive = hiveService.retrieveHiveById(Integer.parseInt(text));
        setValue(hive);
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }
}
