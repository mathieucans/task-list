package com.codurance.training.tasks.command

class CommandAddTask(val project: String, val description: String) : Command {
    override fun execute(executor: CommandExecutor) {
        executor.addTask(project, description)
    }
}
