package com.example.gptplugin

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPasswordField
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class ChatGPTDialogWrapper : DialogWrapper(true) {

    val panel = JPanel(GridBagLayout())
    val txtMode = JTextField()
    val txtUsername = JTextField()
    val txtPassword = JBPasswordField()

    init {
        init()
        title = "Chat GPT"
        val state = ChatGPTPluginSettings.getInstance().pluginState
        txtMode.text = state.mode


        val credentialAttributes = CredentialAttributes("ChatGPTPlugin", "mykey")
        val credentials = PasswordSafe.instance.get(credentialAttributes)

        txtPassword.text = credentials?.getPasswordAsString()
        txtUsername.text = credentials?.userName
    }

    override fun createCenterPanel(): JComponent {
        val gb = GridBag()
            .setDefaultInsets(JBUI.insets(0, 0, UIUtil.DEFAULT_VGAP, UIUtil.DEFAULT_HGAP))
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBag.BOTH)

        panel.preferredSize = JBUI.size(400, 200)

        panel.add(label("mode"), gb.nextLine().next().weightx(0.2))
        panel.add(txtMode, gb.next().next().weightx(0.8))
        panel.add(label("username"), gb.nextLine().next().weightx(0.2))
        panel.add(txtUsername, gb.next().next().weightx(0.8))
        panel.add(label("password"), gb.nextLine().next().weightx(0.2))
        panel.add(txtPassword, gb.next().next().weightx(0.8))

        return panel
    }

    override fun doOKAction() {
        val mode = txtMode.text
        val username = txtUsername.text
        val password = txtPassword.text
        val state = ChatGPTPluginSettings.getInstance().pluginState
        println("state.mode = ${state.mode}")
        state.mode = mode

        println("mode = $mode")
        println("username = $username")
        println("password = $password")
        println("state.mode = ${state.mode}")

        val credentialAttributes = CredentialAttributes("ChatGPTPlugin", username)
        val credentials = Credentials(username, password)
        PasswordSafe.instance.set(credentialAttributes, credentials)
    }

    private fun label(text: String): JComponent {
        return JBLabel(text).apply {
            componentStyle = UIUtil.ComponentStyle.SMALL
            fontColor = UIUtil.FontColor.BRIGHTER
            border = JBUI.Borders.empty(0, 5, 2, 0)
        }
    }
}