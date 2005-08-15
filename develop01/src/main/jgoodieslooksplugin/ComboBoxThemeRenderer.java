package jgoodieslooksplugin;

import com.jgoodies.looks.plastic.PlasticTheme;

public class ComboBoxThemeRenderer extends LabelListCellRenderer {
    
    protected String getText(Object value) {
        return ((PlasticTheme) value).getName();
    }


    protected String getToolTipText(Object value) {
        return ((PlasticTheme) value).toString();
    }

}