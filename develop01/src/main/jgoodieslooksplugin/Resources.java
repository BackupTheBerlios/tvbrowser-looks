/*
 * Created on 12.08.2005
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jgoodieslooksplugin;

import util.ui.Localizer;
import devplugin.PluginInfo;
import devplugin.Version;

class Resources {

    private static final Localizer mLocalizer = Localizer
            .getLocalizerFor(JGoodiesLooksPlugin.class);

    static final String TITLE = mLocalizer.msg("pluginTitle", "JGoodies Looks Look&Feel");
    static final String NAME = mLocalizer.msg("pluginName", "JGoodies Looks Look&Feel Configuration");
    static final String DESC = mLocalizer.msg("description", "Configures and activates JGoodies Looks L&Fs\nDank an http://www.jgoodies.com/freeware/looks/");
    static final String AUTHOR = "Martin Skopp";
    static final String LICENSE = "GNU General Public License";

    static final String LABEL_ENABLE = mLocalizer.msg("labelEnable", "Use JGoodies L&F");
    static final String LABEL_LAF = mLocalizer.msg("labelLaf", "Look and Feel:");
    static final String LABEL_THEME = mLocalizer.msg("labelTheme", "Theme:");
    static final String LABEL_NOTE = mLocalizer.msg("labelNote", "<html><b>ATTENTION:</b> Changing above settings will require<br>a restart of TV-Browser to apply changes.</html>");

    static final PluginInfo PLUGIN_INFO = new PluginInfo(TITLE, DESC, AUTHOR, new Version(0, 2), LICENSE);

}
