/* $Id: ComboBoxLafRenderer.java,v 1.3 2005/08/20 12:43:13 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import javax.swing.LookAndFeel;

/**
 * Renderer for LookAndFeel Combobox entries.
 * 
 * @author Martin Skopp
 * @version $Revision: 1.3 $
 */
class ComboBoxLafRenderer extends LabelListCellRenderer {

    protected String getText(Object value) {
        return ((LookAndFeel) value).getName();
    }

    protected String getToolTipText(Object value) {
        return ((LookAndFeel) value).getDescription();
    }

}