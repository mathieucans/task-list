package com.codurance.training.tasks.command

import com.codurance.training.tasks.ProjectName

class CommandAddProject(private val commandRest:String) : Command {
    override fun execute(taskList: CommandExecutor) {
        taskList.addProject(ProjectName( commandRest))
    }
}
