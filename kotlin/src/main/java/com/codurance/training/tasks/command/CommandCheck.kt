package com.codurance.training.tasks.command

import com.codurance.training.tasks.TaskId

class CommandCheck: Command {
    val taskId: TaskId

    constructor(commandRest: String) {
        taskId = TaskId(Integer.parseInt(commandRest).toLong())
    }

    override fun execute(executor: CommandExecutor) {
        executor.check(taskId)
    }
}
