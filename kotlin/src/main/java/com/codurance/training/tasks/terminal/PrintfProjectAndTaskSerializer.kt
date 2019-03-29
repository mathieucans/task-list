package com.codurance.training.tasks.terminal

import com.codurance.training.tasks.ProjectAndTasks
import com.codurance.training.tasks.ProjectName
import com.codurance.training.tasks.Task
import java.io.PrintWriter

class PrintfProjectAndTaskSerializer(private val out: PrintWriter) : ProjectAndTasks.ProjectAndTaskSerializer {
    override fun serializeProjectAndTask(projectName: ProjectName, tasks: Collection<Task>) {
        projectName.serialize(PrintfProjectNameSerializer(out))
        for (task in tasks) {
            task.serilizezTask(PrintfTaskSerializer(out))
        }
        out.println()
    }

}
