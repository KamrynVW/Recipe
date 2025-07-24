package com.gui;

import java.awt.Color;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;

public class GUIColorsUtil {
    public static void bindBackgroundToColorManager(JComponent component) {
        component.setBackground(GUIColors.INSTANCE.getBackgroundColor());

        GUIColors.INSTANCE.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if ("backgroundColor".equals(evt.getPropertyName())) {
                component.setBackground((Color) evt.getNewValue());
                component.repaint();
            }
        });
    }

    public static void bindTextToColorManager(JComponent component) {
        component.setForeground(GUIColors.INSTANCE.getTextColor());

        GUIColors.INSTANCE.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if ("textColor".equals(evt.getPropertyName())) {
                component.setForeground((Color) evt.getNewValue());
                component.repaint();
            }
        });
    }
}
