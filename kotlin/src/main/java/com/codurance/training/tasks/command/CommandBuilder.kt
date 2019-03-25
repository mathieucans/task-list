package com.codurance.training.tasks.command

class CommandBuilder {
    fun build(commandLine: String): Command {
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


}
