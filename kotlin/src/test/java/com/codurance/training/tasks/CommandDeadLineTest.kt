package com.codurance.training.tasks

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class CommandDeadLineTest
{
    @Test
    fun extract_taskid_from_command_rest()
    {
        val commandDeadLine = CommandDeadLine("10 18/02/2019 20:30:00")

        assertThat(commandDeadLine.taskId).isEqualTo(TaskId(10))
    }
}
