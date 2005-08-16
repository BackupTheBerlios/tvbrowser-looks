/* $Id: JGoodiesLooksPlugin.java,v 1.3 2005/08/16 20:37:18 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Properties;

import devplugin.Plugin;
import devplugin.PluginInfo;
import devplugin.SettingsTab;

/**
 * The plugin itself.
 *
 * @author  Martin Skopp
 * @version $Revision: 1.3 $
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
        URL u1 = getClass().getResource("jgoodieslooksplugin/jgoodies-icon.gif");
        System.out.println("URL=" + u1);
        URL u2 = JGoodiesLooksPlugin.class.getClassLoader().getParent().getResource(this.getClass().getName());
        System.out.println("URL=" + u2);
        System.out.println("CP=" + System.getProperty("java.class.path"));
        System.setProperty("java.class.path", System.getProperty("java.class.path") + ":" + u1 + ":" + u2);
        System.out.println("CP=" + System.getProperty("java.class.path"));
        
	    config = new Configuration(props);
	    config.activateLaf();
	}

    public static void main(String[] args) {
        final Frame f = new Frame();
        final SettingsTab s = new JGoodiesLooksPlugin().getSettingsTab();  
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