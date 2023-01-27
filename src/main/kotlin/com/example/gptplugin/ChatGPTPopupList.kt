package com.example.gptplugin

import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

class ChatGPTPopupList(title: String, fruits: List<String>) : BaseListPopupStep<String>(title, fruits) {

    override fun isSpeedSearchEnabled(): Boolean {
        return true
    }

    override fun onChosen(selectedValue: String?, finalChoice: Boolean): PopupStep<*>? {
        println("in onChosen")
        return PopupStep.FINAL_CHOICE
    }

}