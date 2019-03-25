package com.codurance.training.tasks.command

class UnknownCommand(val command: String) : Command
{
    override fun execute(executor: CommandExecutor) {
        executor.error(command)
    }
}
