package com.gui;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public enum GUIColors {
    INSTANCE;

    private Color backgroundColor = new Color(0x27282c);
    private Color textColor = new Color(0xFF5F1F);
    private Color borderColor = new Color(0x27282c);
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void setBackgroundColor(Color clr) {
        Color oldColor = this.backgroundColor;
        this.backgroundColor = clr;
        pcs.firePropertyChange("backgroundColor", oldColor, clr);
    }

    public void setTextColor(Color clr) {
        Color oldColor = this.textColor;
        this.textColor = clr;
        pcs.firePropertyChange("textColor", oldColor, clr);
    }

    public void setBorderColor(Color clr) {
        Color oldColor = this.borderColor;
        this.borderColor = clr;
        pcs.firePropertyChange("borderColor", oldColor, clr);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
