package com.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title",nullable = false, length = 150)
    private String title;
    @Lob
    @Column(name = "Content",nullable = false,columnDefinition = "TEXT")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on")
    private Date createOn = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_on")
    private Date updateOn = new Date();
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
