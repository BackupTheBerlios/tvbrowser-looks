package jgoodieslooksplugin;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

abstract public class LabelListCellRenderer implements ListCellRenderer {
    
    private final JPanel panel;
    private final JLabel label;
    
    public LabelListCellRenderer() {
        panel = new JPanel();
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createEmptyBorder());

        label = new JLabel();
        panel.setLayout(new FlowLayout());
        panel.add(label, FlowLayout.LEFT);
    }
    
    abstract protected String getText(Object value); 
    abstract protected String getToolTipText(Object value); 

    public final Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        panel.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        panel.setEnabled(list.isEnabled());
        panel.setFont(list.getFont());

        label.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

        String text = value == null ? "" : getText(value);
        label.setText(text == null ? "" : text);

        String tip = value == null ? "" : getToolTipText(value);
        label.setToolTipText(tip == null ? "" : tip);
        return panel;
    }

}