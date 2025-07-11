package com.plugindev.autogeneratecode;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

public class OpenAutoGenerateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        // 创建并显示 Swing 窗体
        JFrame frame = new JFrame("Auto Generate Code");
        frame.setContentPane(new autoGenerateCode().JPanel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // 居中显示
        frame.setVisible(true);
    }
}
