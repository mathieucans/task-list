package com.codurance.training.tasks

import com.codurance.training.tasks.command.*
import com.codurance.training.tasks.terminal.PrintfTaskIdSerializer
import com.codurance.training.tasks.terminal.PrintfTaskSerializer
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

class TaskList(private val `in`: BufferedReader, private val out: PrintWriter) : Runnable, CommandExecutor {

    private val tasks = LinkedHashMap<String, MutableList<Task>>()

    private var lastId: Long = 0

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

            val command = buildCommand(commandLine)
            command.execute(this)
        }
    }

    private fun buildCommand(commandLine: String): Command {
        val commandRest = commandLine.split(" ".toRegex(), 2).toTypedArray()
        val command = commandRest[0]
        return when (command) {
            "show" -> CommandShow()
            "help" -> CommandHelp()
            "add" -> buildCommandAdd(commandRest[1])
            "check" -> CommandCheck(commandRest[1])
            "uncheck" -> CommandUncheck(commandRest[1])
            else -> UnknownCommand(command)
        }
    }

    private fun buildCommandAdd(commandLine: String): Command {
        val subcommandRest = commandLine.split(" ".toRegex(), 2).toTypedArray()
        val subcommand = subcommandRest[0]
        if (subcommand == "project") {
            return CommandAddProject(subcommandRest[1])
        } else if (subcommand == "task") {
            val projectTask = subcommandRest[1].split(" ".toRegex(), 2).toTypedArray()
            return CommandAddTask(projectTask[0], projectTask[1])
        } else
            return UnknownCommand(commandLine)
    }

    override fun show() {
        for ((key, value) in tasks) {
            out.println(key)
            for (task in value) {
                task.serilizezTask(PrintfTaskSerializer(out))
            }
            out.println()
        }
    }

    override fun addProject(name: String) {
        tasks[name] = ArrayList()
    }

    override fun addTask(project: String, description: String) {
        val projectTasks = tasks[project]
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project)
            out.println()
            return
        }
        projectTasks.add(Task(nextId(), description, false))
    }

    override fun check(taskId: TaskId) {
        setDone(true, taskId)
    }

    override fun uncheck(taskId: TaskId) {
        setDone(false, taskId)
    }

    private fun setDone(done: Boolean, taskId: TaskId) {
        for ((_, value) in tasks) {
            for (task in value) {
                if (task.id == taskId) {
                    task.isDone = done
                    return
                }
            }
        }
        taskId.serialize(PrintfTaskIdSerializer(out, "Could not find a task with an ID of %d."))
        out.println()
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

    private fun nextId(): TaskId {
        return TaskId(++lastId)
    }

    companion object {
        private val QUIT = "quit"

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val `in` = BufferedReader(InputStreamReader(System.`in`))
            val out = PrintWriter(System.out)
            TaskList(`in`, out).run()
        }
    }
}
