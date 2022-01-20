package com.asianfo.springsamples.propertyEditor;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {
    private Object value;
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.hasLength(text)) {
            String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);
            try {
                Date d = sdf.parse(text);
                setValue(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return;
        }

        setValue(text);
    }
    public void setValue(Object value) {
        this.value = value;
        firePropertyChange();
    }
    public String getAsText() {
        return (this.value != null)
                ? this.value.toString()
                : null;
    }
    public Object getValue() {
        return value;
    }
}
