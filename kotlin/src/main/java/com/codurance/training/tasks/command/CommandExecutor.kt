package com.codurance.training.tasks.command

import com.codurance.training.tasks.ProjectName
import com.codurance.training.tasks.TaskId

interface CommandExecutor {
    fun show()
    fun help()
    fun addProject(project: ProjectName)
    fun addTask(project: ProjectName, description: String)
    fun check(taskId: TaskId)
    fun uncheck(taskId: TaskId)
    fun error(command: String)

}
