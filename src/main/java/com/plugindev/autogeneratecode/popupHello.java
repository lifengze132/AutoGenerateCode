package com.plugindev.autogeneratecode;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class popupHello extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Messages.showInfoMessage("Hello from MyAction!", "Title");
    }
}
