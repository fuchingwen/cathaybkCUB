package com.coindeskAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@MappedSuperclass
public abstract class BaseEntity<T extends BaseEntity, ID> implements Serializable {

    protected ID id;

    protected LocalDateTime createTime;

    protected LocalDateTime updateTime;

    protected String isSuspend;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Column(name = "create_time", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time", nullable = false, updatable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "is_suspend", length = 1, columnDefinition = "char")
    public String getIsSuspend() {
        return isSuspend;
    }

    public void setIsSuspend(String isSuspend) {
        this.isSuspend = isSuspend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?, ?> that = (BaseEntity<?, ?>) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(isSuspend, that.isSuspend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isSuspend);
    }
}
