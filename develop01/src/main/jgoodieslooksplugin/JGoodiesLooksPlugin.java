/*
 * Created on 06.08.2005
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jgoodieslooksplugin;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Properties;

import devplugin.Plugin;
import devplugin.PluginInfo;
import devplugin.SettingsTab;

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
        Properties settings = tab == null ? config : tab.getConfiguration();
        Enumeration e = settings.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            System.out.println("store " + key + "=" + settings.getProperty(key));
        }
        return settings;
    }
    
	public void loadSettings(Properties props) {
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