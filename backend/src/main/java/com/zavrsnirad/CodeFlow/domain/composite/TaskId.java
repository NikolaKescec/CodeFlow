package com.zavrsnirad.CodeFlow.domain.composite;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TaskId implements Serializable {

    private UUID taskId;

    private UUID owner;

    public TaskId(UUID taskId, UUID owner) {
        this.taskId = taskId;
        this.owner = owner;
    }

    public TaskId() {
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId1 = (TaskId) o;
        return Objects.equals(taskId, taskId1.taskId) && Objects.equals(owner, taskId1.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, owner);
    }
}
