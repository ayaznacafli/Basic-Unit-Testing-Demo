package com.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false,length = 150)
    private String name;
    @Column(name = "email",nullable = false,length = 150)
    private String email;
    @Lob
    @Column(name = "content",nullable = false, columnDefinition = "TEXT")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on")
    private Date createOn;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_on")
    private Date updateOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }
}
