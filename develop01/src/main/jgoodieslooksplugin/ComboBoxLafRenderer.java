/* $Id: ComboBoxLafRenderer.java,v 1.2 2005/08/16 20:37:18 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import javax.swing.LookAndFeel;

/**
 * Renderer for LookAndFeel Combobox entries.
 *
 * @author  Martin Skopp
 * @version $Revision: 1.2 $
 */
class ComboBoxLafRenderer extends LabelListCellRenderer {

    protected String getText(Object value) {
        return ((LookAndFeel) value).getName();
    }


    protected String getToolTipText(Object value) {
        return ((LookAndFeel) value).getDescription();
    }

}