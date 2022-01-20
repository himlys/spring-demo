package com.asianfo.springsamples.propertyEditor;

import java.beans.PropertyEditorSupport;

public class UserPropertyEditor extends PropertyEditorSupport {
    private Object value;
    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        if(text.equals("A")){
            setValue(120);
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
