/* $Id: LabelListCellRenderer.java,v 1.5 2005/08/20 12:43:13 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * General Renderer for Combobox entries.
 * 
 * @author Martin Skopp
 * @version $Revision: 1.5 $
 */
abstract class LabelListCellRenderer implements ListCellRenderer {

    private final JLabel label;

    LabelListCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }

    abstract protected String getText(Object value);

    abstract protected String getToolTipText(Object value);

    public final Component getListCellRendererComponent(JList list,
        Object value, int index, boolean isSelected, boolean cellHasFocus)
    {

        label.setEnabled(list.isEnabled());
        label.setFont(list.getFont());
        label.setBackground(isSelected ? list.getSelectionBackground() : list
            .getBackground());
        label.setForeground(isSelected ? list.getSelectionForeground() : list
            .getForeground());

        String text = value == null ? "" : getText(value);
        label.setText(text == null ? "" : text);

        String tip = value == null ? "" : getToolTipText(value);
        label.setToolTipText(tip == null ? "" : tip);
        return label;
    }

}