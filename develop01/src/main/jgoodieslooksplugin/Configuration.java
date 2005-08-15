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

class Configuration extends Properties {
    
    private static final String LAF = "laf";
    private static final String THEME = "theme";
    private static final String ACTIVE = "active";
    
    private LookAndFeel otherLaf;

    Configuration(Properties p) {
        super(p);
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
        setProperty(ACTIVE, "" + activ);
    }
    
    boolean isActive() {
        return Boolean.TRUE.toString().equals(getProperty(ACTIVE, "false"));
    }

    void setLaf(LookAndFeel laf) {
        setProperty(LAF, laf.getClass().getName());
    }
    
    LookAndFeel getLaf() {
        String clazz = getProperty(LAF, null); 
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
        setProperty(THEME, theme.getClass().getName());
    }

    PlasticTheme getTheme() {
        String clazz = getProperty(THEME, null); 
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
