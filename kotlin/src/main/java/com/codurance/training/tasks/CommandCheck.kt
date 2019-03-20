package com.codurance.training.tasks

class CommandCheck: Command {
    val taskId: TaskId

    constructor(commandRest: String) {
        taskId = TaskId(Integer.parseInt(commandRest).toLong())
    }

}
