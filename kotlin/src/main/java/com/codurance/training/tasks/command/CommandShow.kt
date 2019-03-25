package com.codurance.training.tasks.command

class CommandShow : Command {
    override fun execute(executor: CommandExecutor) {
        executor.show()
    }

}
