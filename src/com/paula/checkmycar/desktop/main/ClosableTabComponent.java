package com.paula.checkmycar.desktop.main;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ClosableTabComponent extends JPanel {

    private static final long serialVersionUID = 1L;

    public ClosableTabComponent(JTabbedPane tabbedPane,
                                String title) {

        setOpaque(false);

        setLayout(new FlowLayout(
                FlowLayout.LEFT,
                0,
                0));

        JLabel titleLabel = new JLabel(title) {

            private static final long serialVersionUID = 1L;

            @Override
            public String getText() {

                int index =
                        tabbedPane.indexOfTabComponent(
                                ClosableTabComponent.this);

                if (index != -1) {

                    return tabbedPane.getTitleAt(index);
                }

                return "";
            }
        };

        JButton closeButton = new JButton(" x ");

        closeButton.setBorder(null);

        closeButton.setFocusable(false);

        closeButton.setContentAreaFilled(false);

        closeButton.addActionListener(e -> {

            int index =
                    tabbedPane.indexOfTabComponent(
                            ClosableTabComponent.this);

            if (index != -1) {

                tabbedPane.remove(index);
            }
        });

        add(titleLabel);

        add(closeButton);
    }
}