package com.example.gptplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.JBPopupFactory

class HelloAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val popupList = ChatGPTPopupList("Choose a fruit", listOf("Apple", "Banana", "Orange"))
        if (e.project != null) {
            JBPopupFactory.getInstance()
                .createListPopup(popupList, 5)
                .showCenteredInCurrentWindow(e.project!!)
        } else {
            println("in unexpected null e.project")
        }
    }

    private fun showCustomDialog(e: AnActionEvent) {
        val dialogWrapper = ChatGPTDialogWrapper()
        println("We are here in hello action")
        if (dialogWrapper.showAndGet()) {
            println("hello action in detailed")
            dialogWrapper.close(0)
        } else {
            println("in else hello action")
            dialogWrapper.close(0)
        }
    }

    private fun getUsername(e: AnActionEvent) {
        val name =
            Messages.showInputDialog(e.project, "What is your name?", "Input Your Name", Messages.getQuestionIcon())
        Messages.showMessageDialog(e.project, "Hello, $name!", "Information", Messages.getInformationIcon())
    }

    private fun showFileDialog(e: AnActionEvent) {
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
            Messages.showMessageDialog(
                e.project,
                "You selected ${it.path}",
                "Information",
                Messages.getInformationIcon()
            )
        }
    }
}