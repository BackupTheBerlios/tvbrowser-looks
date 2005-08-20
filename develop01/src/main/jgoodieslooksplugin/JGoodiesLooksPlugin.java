/* $Id: JGoodiesLooksPlugin.java,v 1.6 2005/08/20 12:43:13 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.theme.Silver;

import devplugin.Plugin;
import devplugin.PluginInfo;
import devplugin.SettingsTab;

/**
 * The plugin itself.
 * 
 * @author Martin Skopp
 * @version $Revision: 1.6 $
 */
public class JGoodiesLooksPlugin extends Plugin {

    private Configuration config;
    private ConfigureTab tab;

    public PluginInfo getInfo() {
        return Resources.PLUGIN_INFO;
    }

    public SettingsTab getSettingsTab() {
        if (tab == null) {
            tab = new ConfigureTab(config);
        }
        return tab;
    }

    public Properties storeSettings() {
        return config.getProperties();
    }

    public void loadSettings(Properties props) {
        config = new Configuration(props);
        config.activateLaf();
    }

    public static void main(String[] args) {
        final Frame f = new Frame();
        JGoodiesLooksPlugin plugin = new JGoodiesLooksPlugin();
        Configuration config = new Configuration(new Properties());
        config.setActive(true);
        config.setLaf(new Plastic3DLookAndFeel());
        config.setTheme(new Silver());
        plugin.loadSettings(config.getProperties());
        final SettingsTab s = plugin.getSettingsTab();
        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                s.saveSettings();
                f.hide();
                System.exit(0);
            }

        });
        f.add(s.createSettingsPanel());
        f.pack();
        f.show();
    }

}