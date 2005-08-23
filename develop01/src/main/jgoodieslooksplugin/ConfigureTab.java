/* $Id: ConfigureTab.java,v 1.8 2005/08/23 21:55:07 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTheme;

import util.ui.ImageUtilities;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticTheme;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;

import devplugin.SettingsTab;

/**
 * GUI component to configure plugin.
 * 
 * @author Martin Skopp
 * @version $Revision: 1.8 $
 */
class ConfigureTab extends JPanel implements SettingsTab {

    private final Configuration config;
    private JComboBox lafChoice, themeChoice;
    private JCheckBox looksEnabled, dropShadow;
    private JPanel settingsPanel;
    private boolean initilized = false;

    ConfigureTab(Configuration defaultConfig) {
        config = defaultConfig;
    }

    private static LookAndFeel[] getSupportedLookAndFeels() {
        LookAndFeel[] candidate = new LookAndFeel[]{new WindowsLookAndFeel(),
            new PlasticLookAndFeel(), new Plastic3DLookAndFeel(),
            new PlasticXPLookAndFeel()};

        int max = 0;
        for (int i = 0; i < candidate.length; i++) {
            if (candidate[i].isSupportedLookAndFeel()) {
                max++;
            }
        }

        LookAndFeel[] result = new LookAndFeel[max];
        int current = 0;
        for (int i = 0; i < candidate.length; i++) {
            if (candidate[i].isSupportedLookAndFeel()) {
                result[current] = candidate[i];
                current++;
            }
        }
        return result;
    }

    private void initComponents() {
        final LookAndFeel[] lafs = getSupportedLookAndFeels();
        final Object[] themes = PlasticLookAndFeel.getInstalledThemes()
            .toArray();

        looksEnabled = new JCheckBox(Resources.LABEL_ENABLE);
        looksEnabled.setSelected(config.isActive());

        lafChoice = new JComboBox(lafs);
        lafChoice.setRenderer(new ComboBoxLafRenderer());
        String selectedLaf = config.getLaf().getName();
        for (int i = 0; i < lafs.length; i++) {
            if (lafs[i].getName().equals(selectedLaf)) {
                lafChoice.setSelectedIndex(i);
                break;
            }
        }

        themeChoice = new JComboBox(themes);
        themeChoice.setRenderer(new ComboBoxThemeRenderer());
        String selectedTheme = config.getTheme().getName();
        for (int i = 0; i < themes.length; i++) {
            if (((MetalTheme) themes[i]).getName().equals(selectedTheme)) {
                themeChoice.setSelectedIndex(i);
                break;
            }
        }

        dropShadow = new JCheckBox(Resources.LABEL_DROP_SHADOW);
        dropShadow.setSelected(config.isPopupDropShadowEnabled());
    }

    public JPanel createSettingsPanel() {
        if (!initilized) {
            /*
             * TV-Browser invokes (at least "1.1alpha3" did) this method
             * multiple times. Initializing components again would reset
             * selected values in UI components
             */
            initilized = true;
            initComponents();

            FormLayout layout = new FormLayout("pref, 4dlu, pref",
                "pref, 2dlu, pref, 2dlu, pref, 2dlu, pref, 2dlu, pref");
            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();
            
            builder.addSeparator(Resources.NAME,    cc.xyw(1, 1, 3));
            
            builder.add(looksEnabled,               cc.xy(3, 3));
            
            builder.addLabel(Resources.LABEL_LAF,   cc.xy(1, 5));
            builder.add(lafChoice,                  cc.xy(3, 5));
            
            builder.addLabel(Resources.LABEL_THEME, cc.xy(1, 7));
            builder.add(themeChoice,                cc.xy(3, 7));
            
            builder.add(dropShadow,                 cc.xy(3, 9));
            
            settingsPanel = builder.getPanel();
        }

        return settingsPanel;
    }

    public void saveSettings() {
        config.setActive(looksEnabled.isSelected());
        config.setLaf((LookAndFeel) lafChoice.getSelectedItem());
        config.setTheme((PlasticTheme) themeChoice.getSelectedItem());
        config.setPopupDropShadow(dropShadow.isSelected());
        config.activateLaf();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                SwingUtilities.updateComponentTreeUI(createSettingsPanel());
            }
        });
    }

    public Icon getIcon() {
        return new ImageIcon(ImageUtilities.createImageFromJar(
            "jgoodieslooksplugin/jgoodies-icon.gif", getClass()));
    }

    public String getTitle() {
        return Resources.TITLE;
    }
}
