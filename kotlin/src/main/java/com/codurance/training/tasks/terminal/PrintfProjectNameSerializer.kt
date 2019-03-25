package com.codurance.training.tasks.terminal

import com.codurance.training.tasks.ProjectName
import java.io.PrintWriter

class PrintfProjectNameSerializer(private val out: PrintWriter) : ProjectName.ProjectNameSerializer {
    override fun serializeProjectName(name: String) {
        out.println(name)
    }

}
