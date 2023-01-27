package com.example.gptplugin

import com.intellij.openapi.components.ComponentManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(
    name = "ChatGPTPluginState",
    storages = [Storage("plugin.xml")]
)
class ChatGPTPluginSettings : PersistentStateComponent<ChatGPTPluginState> {

    companion object {
        @JvmStatic
        fun getInstance() = ServiceManager.getService(ChatGPTPluginSettings::class.java)
    }

    var pluginState = ChatGPTPluginState()

    override fun getState(): ChatGPTPluginState {
        return pluginState
    }

    override fun loadState(state: ChatGPTPluginState) {
        pluginState = state
    }
}