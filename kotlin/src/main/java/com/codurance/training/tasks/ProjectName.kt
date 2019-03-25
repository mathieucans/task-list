package com.codurance.training.tasks

class ProjectName(private val name: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectName

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    fun serialize(serializer: ProjectNameSerializer) {
        serializer.serializeProjectName(name)
    }

    interface ProjectNameSerializer {
        fun serializeProjectName(name: String)
    }
}
