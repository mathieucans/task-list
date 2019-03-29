package com.codurance.training.tasks

import com.codurance.training.tasks.terminal.PrintfProjectNameSerializer
import com.codurance.training.tasks.terminal.PrintfTaskIdSerializer
import com.codurance.training.tasks.terminal.PrintfTaskSerializer
import java.util.ArrayList

class ProjectAndTasks {
    fun addProject(name: ProjectName) {
        tasks[name] = ArrayList()
    }

    fun addTask(project: ProjectName, description: String): Boolean {
        val projectTasks = tasks[project]

        if (projectTasks != null)
        {
            projectTasks.add(Task(TaskId.nextId(), description, false))
        }
        return projectTasks != null
    }

    fun serialize(serializer: ProjectAndTaskSerializer) {
        for ((key, value) in tasks) {
            serializer.serializeProjectAndTask(key, value)
        }
    }

    fun setDone(done: Boolean, taskId: TaskId): Boolean {
        for ((_, value) in tasks) {
            for (task in value) {
                if (task.id == taskId) {
                    task.isDone = done
                    return true
                }
            }
        }
        return false
    }

    interface ProjectAndTaskSerializer {
        fun serializeProjectAndTask(projectName: ProjectName, tasks: Collection<Task>)
    }

    private val tasks = LinkedHashMap<ProjectName, MutableList<Task>>()
}
