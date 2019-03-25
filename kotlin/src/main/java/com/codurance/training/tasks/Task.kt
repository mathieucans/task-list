package com.codurance.training.tasks

class Task(val id: TaskId, val description: String, var isDone: Boolean)
{
    fun serilizezTask(serializer:TaskSerializer) {
        serializer.serializeTask(id, description, isDone)
    }

    interface TaskSerializer {
        fun serializeTask(id: TaskId, description: String, done: Boolean)
    }
}
