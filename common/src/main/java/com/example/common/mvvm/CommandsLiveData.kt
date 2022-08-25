package com.example.common.mvvm

import androidx.lifecycle.LiveData
import java.util.LinkedList

class CommandsLiveData<T> : LiveData<LinkedList<T>>() {

    fun onNext(command: T) {
        var commands = value
        if (commands == null) {
            commands = LinkedList()
        }
        commands.add(command)
        value = commands
    }
}