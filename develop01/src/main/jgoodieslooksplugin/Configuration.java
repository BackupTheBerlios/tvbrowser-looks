/*
 * Created on 11.08.2005
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jgoodieslooksplugin;

import java.util.Properties;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticTheme;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

final class Configuration {
    
    private static final String LAF = "laf";
    private static final String THEME = "theme";
    private static final String ACTIVE = "active";
    private Properties p;
    
    private LookAndFeel otherLaf;

    Configuration(Properties props) {
        p = props;
        /*
         * initialize all default values.  This ensures all values are 
         * always there and always stores and always load
         */
        setLaf(getLaf());
        setTheme(getTheme());
        setActive(isActive());
    }
    
    Properties getProperties() {
        return p;
    }
    
    void activateLaf() {
		if (isActive()) {
	        otherLaf = UIManager.getLookAndFeel();
			PlasticLookAndFeel.setMyCurrentTheme(getTheme());
			try {
	            UIManager.setLookAndFeel(getLaf());
	        } catch (UnsupportedLookAndFeelException e) {
	            e.printStackTrace();
	        }
        } else {
            if (otherLaf != null) {
                try {
                    UIManager.setLookAndFeel(otherLaf);
                } catch (UnsupportedLookAndFeelException e) {
    	            e.printStackTrace();
                }
            }
        }

    }
    
    void setActive(boolean activ) {
        p.setProperty(ACTIVE, "" + activ);
    }
    
    boolean isActive() {
        return Boolean.TRUE.toString().equals(p.getProperty(ACTIVE, "false"));
    }

    void setLaf(LookAndFeel laf) {
        p.setProperty(LAF, laf.getClass().getName());
    }
    
    LookAndFeel getLaf() {
        String clazz = p.getProperty(LAF, null); 
        if (clazz == null) {
            return new PlasticXPLookAndFeel();
        }
        try {
            return (LookAndFeel) Class.forName(clazz).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    void setTheme(PlasticTheme theme) {
        p.setProperty(THEME, theme.getClass().getName());
    }

    PlasticTheme getTheme() {
        String clazz = p.getProperty(THEME, null); 
        if (clazz == null) {
            return PlasticLookAndFeel.getMyCurrentTheme();
        }
        try {
            return (PlasticTheme) Class.forName(clazz).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
