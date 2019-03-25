package com.codurance.training.tasks

class TaskId(private val id: Long) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TaskId

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun serialize(serializer: TaskIdSerializer) {
        serializer.serializeTaskId(id)
    }

    interface TaskIdSerializer {
        fun serializeTaskId(id: Long)
    }

    companion object {
        private var lastId: Long = 0

        fun nextId(): TaskId {
            return TaskId(++lastId)
        }
    }
}
