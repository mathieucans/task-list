package com.codurance.training.tasks

class CommandUncheck : Command {

    val taskId: TaskId

    constructor(commandRest: String) {
        taskId = TaskId(Integer.parseInt(commandRest).toLong())
    }

}
