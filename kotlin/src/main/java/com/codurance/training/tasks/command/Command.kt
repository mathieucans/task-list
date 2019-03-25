package com.codurance.training.tasks.command

interface Command {
    fun execute(executor: CommandExecutor)
}
