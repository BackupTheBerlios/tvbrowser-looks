/* $Id: ComboBoxThemeRenderer.java,v 1.3 2005/08/20 12:43:13 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import com.jgoodies.looks.plastic.PlasticTheme;

/**
 * Renderer for LookAndFeel-Themes Combobox entries.
 * 
 * @author Martin Skopp
 * @version $Revision: 1.3 $
 */
class ComboBoxThemeRenderer extends LabelListCellRenderer {

    protected String getText(Object value) {
        return ((PlasticTheme) value).getName();
    }

    protected String getToolTipText(Object value) {
        return ((PlasticTheme) value).toString();
    }

}