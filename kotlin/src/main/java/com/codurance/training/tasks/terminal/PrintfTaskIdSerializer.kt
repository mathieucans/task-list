package com.codurance.training.tasks.terminal

import com.codurance.training.tasks.TaskId
import java.io.PrintWriter

class PrintfTaskIdSerializer(private val out: PrintWriter, format: String) : TaskId.TaskIdSerializer {
    private val format = format

    override fun serializeTaskId(id: Long) {
        out.printf(format, id)
    }
}
