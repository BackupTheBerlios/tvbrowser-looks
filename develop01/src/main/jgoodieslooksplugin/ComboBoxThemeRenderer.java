/* $Id: ComboBoxThemeRenderer.java,v 1.2 2005/08/16 20:37:18 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import com.jgoodies.looks.plastic.PlasticTheme;

/**
 * Renderer for LookAndFeel-Themes Combobox entries.
 *
 * @author  Martin Skopp
 * @version $Revision: 1.2 $
 */
class ComboBoxThemeRenderer extends LabelListCellRenderer {
    
    protected String getText(Object value) {
        return ((PlasticTheme) value).getName();
    }


    protected String getToolTipText(Object value) {
        return ((PlasticTheme) value).toString();
    }

}