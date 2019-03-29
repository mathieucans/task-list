package com.codurance.training.tasks

import com.codurance.training.tasks.command.CommandBuilder
import com.codurance.training.tasks.command.CommandExecutor
import com.codurance.training.tasks.terminal.PrintfProjectAndTaskSerializer
import com.codurance.training.tasks.terminal.PrintfTaskIdSerializer
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter

class TaskList(private val `in`: BufferedReader, private val out: PrintWriter) : Runnable, CommandExecutor {

    private val tasks = ProjectAndTasks()


    override fun run() {
        while (true) {
            out.print("> ")
            out.flush()
            val commandLine: String
            try {
                commandLine = `in`.readLine()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

            if (commandLine == QUIT) {
                break
            }

            CommandBuilder().build(commandLine).execute(this)
        }
    }

    override fun show() {
        tasks.serialize(PrintfProjectAndTaskSerializer(out))
    }

    override fun addProject(name: ProjectName) {
        tasks.addProject(name)
    }

    override fun addTask(project: ProjectName, description: String) {

        if (!tasks.addTask(project, description)) {
            out.printf("Could not find a project with the name \"%s\".", project)
            out.println()
            return
        }
    }

    override fun check(taskId: TaskId) {
        setDone(true, taskId)
    }

    override fun uncheck(taskId: TaskId) {
        setDone(false, taskId)
    }

    private fun setDone(done: Boolean, taskId: TaskId) {
        if (!tasks.setDone(done, taskId)) {
            taskId.serialize(PrintfTaskIdSerializer(out, "Could not find a task with an ID of %d."))
            out.println()
        }
    }

    override fun help() {
        out.println("Commands:")
        out.println("  show")
        out.println("  add project <project name>")
        out.println("  add task <project name> <task description>")
        out.println("  check <task ID>")
        out.println("  uncheck <task ID>")
        out.println()
    }

    override fun error(command: String) {
        out.printf("I don't know what the command \"%s\" is.", command)
        out.println()
    }

    companion object {
        private const val QUIT = "quit"

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val `in` = BufferedReader(InputStreamReader(System.`in`))
            val out = PrintWriter(System.out)
            TaskList(`in`, out).run()
        }
    }
}
