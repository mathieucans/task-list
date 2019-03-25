package com.codurance.training.tasks


import com.codurance.training.tasks.terminal.TaskIdToLongSerializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TaskIdTest
{
    @Test
    fun should_deserialize_id_as_long()
    {
        val task = TaskId(1234)

        val longSerializer = TaskIdToLongSerializer()
        task.serialize(longSerializer)

        assertThat(longSerializer.value).isEqualTo(1234)
    }

}
