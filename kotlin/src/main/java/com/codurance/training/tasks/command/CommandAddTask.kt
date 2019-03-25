package com.codurance.training.tasks.command

import com.codurance.training.tasks.ProjectName

class CommandAddTask(private val project: String, private val description: String) : Command {
    override fun execute(executor: CommandExecutor) {
        executor.addTask(ProjectName( project ), description)
    }
}
