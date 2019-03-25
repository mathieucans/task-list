package com.codurance.training.tasks.terminal

import com.codurance.training.tasks.Task
import com.codurance.training.tasks.TaskId
import java.io.PrintWriter

class PrintfTaskSerializer(private val out: PrintWriter) : Task.TaskSerializer {
    override fun serializeTask(id: TaskId, description: String, done: Boolean) {

        out.printf("    [%c]", if (done) 'x' else ' ')

        id.serialize(PrintfTaskIdSerializer(out, " %d: "))

        out.printf(description)
        out.println()

    }
}
