/* $Id: Resources.java,v 1.11 2005/08/25 08:02:31 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import util.ui.Localizer;
import devplugin.PluginInfo;
import devplugin.Version;

/**
 * Convenience class localizable strings.
 *
 * @author  Martin Skopp
 * @version $Revision: 1.11 $
 */
class Resources {

    private static final Localizer mLocalizer = Localizer
        .getLocalizerFor(JGoodiesLooksPlugin.class);

    static final String TITLE = mLocalizer.msg("pluginTitle", "JGoodies Looks Look&Feel");
    static final String SETTINGS = mLocalizer.msg("settings", "Settings");
    static final String DESC = mLocalizer.msg("description", "Configures and activates JGoodies Looks L&Fs\nConfigures and activates JGoodies Looks L&Fs\nCopyright (c) 2005 JGoodies Karsten Lentzsch.\nAll Rights Reserved.");
    static final String AUTHOR = "Martin Skopp";
    static final String LICENSE = "GNU General Public License";

    static final String LABEL_ENABLE = mLocalizer.msg("labelEnable", "Use JGoodies L&F");
    static final String LABEL_LAF = mLocalizer.msg("labelLaf", "Look and Feel:");
    static final String LABEL_THEME = mLocalizer.msg("labelTheme", "Theme:");
    static final String LABEL_DROP_SHADOW = mLocalizer.msg("labelDropShadow", "Popup Menu Drop Shadow");

    static final PluginInfo PLUGIN_INFO = new PluginInfo(TITLE, DESC, AUTHOR,
        new Version(0, 8), LICENSE);

}
