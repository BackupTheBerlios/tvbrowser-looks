/* $Id: Configuration.java,v 1.7 2005/08/20 12:43:13 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import java.util.Properties;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.LookUtils;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticTheme;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

/**
 * Convenience class for accessing and mutating plugins settins.
 * 
 * @author Martin Skopp
 * @version $Revision: 1.7 $
 */
final class Configuration {

    /**
     * Default value for activation of JGoodies L&F
     */
    private static final Boolean DEFAULT_ACTIVATION_STATE = Boolean.TRUE;

    private static final String LAF = "laf";
    private static final String THEME = "theme";
    private static final String ACTIVE = "active";
    private static final String POPUP_DROP_SHADOW = "popupDropShadow";
    private Properties p;

    private LookAndFeel otherLaf;

    Configuration(Properties props) {
        p = props;
        /*
         * initialize all default values. This ensures all values are always
         * there and always stores and always load
         */
        setLaf(getLaf());
        setTheme(getTheme());
        setActive(isActive());
        setPopupDropShadow(isPopupDropShadowEnabled());
    }

    Properties getProperties() {
        return p;
    }

    /**
     * Activates JGoodies L&F if enabled or previously enabled L&F if JGoodies
     * L&F is disabled in this configuration.
     */
    void activateLaf() {
        if (isActive()) {
            if (otherLaf == null) {
                otherLaf = UIManager.getLookAndFeel();
            }
            try {
                Options.setPopupDropShadowEnabled(isPopupDropShadowEnabled());
                UIManager.put("jgoodies.popupDropShadowEnabled", Boolean
                    .valueOf(isPopupDropShadowEnabled()));

                LookUtils.setLookAndTheme(getLaf(), getTheme());
                /*
                 * Now inject the classloader of this plugin into the UIManager!
                 * found at
                 * https://lists.xcf.berkeley.edu/lists/advanced-java/2001-January/015380.html
                 * 
                 * Alternative (copied from personal IM chat):
                 * 
                 *  hampelratte: ok, hier mal die lösung von hand:
                 *  hampelratte: URLClassLoader url = (URLClassLoader)Configuration.class.getClassLoader();
                 *  hampelratte: URL[] urls = url.getURLs();
                 *               for (int i = 0; i &lt; urls.length; i++) {
                 *                 System.out.println(urls[i]);
                 *               }
                 * hampelratte: damit kommt man an die datei des plugins ran.
                 * das könnte man dann mit java.util.jar auseinandernehmen und
                 * die ganzen klassen nacheinander laden
                 */
                ClassLoader pluginLoader = getLaf().getClass().getClassLoader();
                UIManager.getDefaults().put("ClassLoader", pluginLoader);
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
            otherLaf = null;
        }

    }

    void setActive(boolean activ) {
        p.setProperty(ACTIVE, "" + activ);
    }

    boolean isActive() {
        return Boolean.TRUE.toString().equals(
            p.getProperty(ACTIVE, DEFAULT_ACTIVATION_STATE.toString()));
    }

    void setLaf(LookAndFeel laf) {
        p.setProperty(LAF, laf.getClass().getName());
    }

    LookAndFeel getLaf() {
        String clazz = p.getProperty(LAF, null);
        if (clazz == null) {
            // the overall default
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
            // the overall default
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

    boolean isPopupDropShadowEnabled() {
        return Boolean.TRUE.toString().equals(
            p.getProperty(POPUP_DROP_SHADOW, ""
                + Options.isPopupDropShadowEnabled()));
    }

    void setPopupDropShadow(boolean activ) {
        p.setProperty(POPUP_DROP_SHADOW, "" + activ);
    }
}
