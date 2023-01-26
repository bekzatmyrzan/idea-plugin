package com.example.gptplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.Messages

class HelloAction: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val fileChooserDescriptor = FileChooserDescriptor(
            false,
            true,
            false,
            false,
            false,
            false
        )
        fileChooserDescriptor.title = "Select a directory"
        fileChooserDescriptor.description = "Select a directory to be used as the root of the project"

        FileChooser.chooseFile(fileChooserDescriptor, e.project, null) {
            Messages.showMessageDialog(e.project, "You selected ${it.path}", "Information", Messages.getInformationIcon())
        }
    }
}