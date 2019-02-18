package com.codurance.training.tasks

class CommandDeadLine : TasklistCommand
{
    val taskId: TaskId

    constructor(commandLine: String)
    {
        val parameters = commandLine.split(" ".toRegex(), 2).toTypedArray()

        taskId = TaskId(Integer.parseInt(parameters[0]))
    }
}
