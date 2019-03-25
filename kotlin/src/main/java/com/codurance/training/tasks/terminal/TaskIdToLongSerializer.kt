package com.codurance.training.tasks.terminal

import com.codurance.training.tasks.TaskId

class TaskIdToLongSerializer : TaskId.TaskIdSerializer {
    override fun serializeTaskId(id: Long) {
        value = id
    }

    var value:Long = 0
}
