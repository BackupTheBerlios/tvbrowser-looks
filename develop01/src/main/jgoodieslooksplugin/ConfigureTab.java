/* $Id: ConfigureTab.java,v 1.5 2005/08/18 06:03:57 emsker Exp $
 *
 * Copyright under GNU General Public License (GPL)
 */
package jgoodieslooksplugin;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SpringLayout;
import javax.swing.plaf.metal.MetalTheme;

import util.ui.ImageUtilities;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticTheme;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;

import devplugin.SettingsTab;

/**
 * GUI component to configure plugin.
 *
 * @author  Martin Skopp
 * @version $Revision: 1.5 $
 */
class ConfigureTab extends JPanel implements SettingsTab {
	
    private final Configuration config;
    private JComboBox lafChoice, themeChoice;
    private JCheckBox looksEnabled, dropShadow;
    private JLabel lafLabel, themeLabel, noteLabel;
    private boolean initilized = false;

    ConfigureTab(Configuration defaultConfig) {
        config = defaultConfig;
    }

    private void initComponents() {
        final LookAndFeel[] lafs;
        final Object[] themes;

        lafs = new LookAndFeel[] { new WindowsLookAndFeel(), new PlasticLookAndFeel(),
                new Plastic3DLookAndFeel(), new PlasticXPLookAndFeel() };

        themes = PlasticLookAndFeel.getInstalledThemes().toArray();

        looksEnabled = new JCheckBox(Resources.LABEL_ENABLE);
        looksEnabled.setSelected(config.isActive());

        lafChoice = new JComboBox(lafs);
        lafChoice.setRenderer(new ComboBoxLafRenderer());
        lafLabel = new JLabel(Resources.LABEL_LAF);
        lafLabel.setLabelFor(lafChoice);
        String selectedLaf = config.getLaf().getName();
        for (int i = 0; i < lafs.length; i++) {
            if (lafs[i].getName().equals(selectedLaf)) {
                lafChoice.setSelectedIndex(i);
                break;
            }
        }

        themeChoice = new JComboBox(themes);
        themeChoice.setRenderer(new ComboBoxThemeRenderer());
        themeLabel = new JLabel(Resources.LABEL_THEME);
        themeLabel.setLabelFor(themeChoice);
        String selectedTheme = config.getTheme().getName();
        for (int i = 0; i < themes.length; i++) {
            if (((MetalTheme)themes[i]).getName().equals(selectedTheme)) {
                themeChoice.setSelectedIndex(i);
                break;
            }
        }

        
        dropShadow = new JCheckBox(Resources.LABEL_DROP_SHADOW);
        dropShadow.setSelected(config.isPopupDropShadowEnabled());
        
        noteLabel = new JLabel(Resources.LABEL_NOTE);
    }

    public JPanel createSettingsPanel() {
        if (!initilized) {
            /*
             * TV-Browser invokes (at least "1.1alpha3" did)
             * this method multiple times.
             * Initializing components again would
             * reset selected values in UI components
             */
            initilized = true;
            initComponents();
            
            JPanel choices = new JPanel();
            choices.setBorder(BorderFactory.createTitledBorder(Resources.NAME));
            choices.setLayout(new SpringLayout());
            
            choices.add(new JLabel());
            choices.add(looksEnabled);
            
            choices.add(lafLabel);
            choices.add(lafChoice);
            
            choices.add(themeLabel);
            choices.add(themeChoice);
            
            choices.add(new JLabel());
            choices.add(dropShadow);
    
            SpringUtilities.makeCompactGrid(choices,
                                            4, 2,   //rows, cols
                                            6, 6,   //initX, initY
                                            6, 6);  //xPad, yPad
            
            setLayout(new BorderLayout());
            
            add(choices, BorderLayout.NORTH);
            
            JPanel note = new JPanel();
            note.setBorder(BorderFactory.createTitledBorder(""));
            note.add(noteLabel);
            add(note, BorderLayout.SOUTH);
        }
        return this;
    }
    
	public void saveSettings() {
	    config.setActive(looksEnabled.isSelected());
	    config.setLaf((LookAndFeel) lafChoice.getSelectedItem());
	    config.setTheme((PlasticTheme) themeChoice.getSelectedItem());
        config.setPopupDropShadow(dropShadow.isSelected());
	}

	public Icon getIcon() {
	    return new ImageIcon(ImageUtilities.createImageFromJar(
	            "jgoodieslooksplugin/jgoodies-icon.gif", getClass()));
	}

	public String getTitle() {
		return Resources.TITLE;
	}
}
