package com.codurance.training.tasks.command

class CommandAddProject(val commandRest:String) : Command {
    override fun execute(taskList: CommandExecutor) {
        taskList.addProject(commandRest)
    }
}
