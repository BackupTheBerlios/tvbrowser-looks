package jgoodieslooksplugin;

import javax.swing.LookAndFeel;

class ComboBoxLafRenderer extends LabelListCellRenderer {

    protected String getText(Object value) {
        return ((LookAndFeel) value).getName();
    }


    protected String getToolTipText(Object value) {
        return ((LookAndFeel) value).getDescription();
    }

}