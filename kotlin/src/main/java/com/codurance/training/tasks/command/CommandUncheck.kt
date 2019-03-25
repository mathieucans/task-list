package com.codurance.training.tasks.command

import com.codurance.training.tasks.TaskId

class CommandUncheck : Command {

    val taskId: TaskId

    constructor(commandRest: String) {
        taskId = TaskId(Integer.parseInt(commandRest).toLong())
    }

    override fun execute(executor: CommandExecutor) {
        executor.uncheck(taskId)
    }

}
