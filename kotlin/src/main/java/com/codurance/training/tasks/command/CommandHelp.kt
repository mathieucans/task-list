package com.codurance.training.tasks.command

class CommandHelp : Command {
    override fun execute(taskList: CommandExecutor) {
        taskList.help()
    }
}
