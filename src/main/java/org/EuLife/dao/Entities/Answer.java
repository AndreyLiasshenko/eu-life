package org.EuLife.dao.Entities;

import org.EuLife.dao.enums.TypeOfLike;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
public class Answer implements Serializable {
    public Answer() {   }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    private Date time;

    private String text;


    @ManyToOne()
    private Question question;

    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private List<Comment> commentList;

    @OneToMany
    @JoinColumn(name = "answer_id")
    private List<Rating> rating;

    public int getRating() {
        int likes = rating.stream().filter(e -> e.getType() == TypeOfLike.LIKE).toArray().length;
        int dislikes = rating.stream().filter(e -> e.getType() == TypeOfLike.DISLIKE).toArray().length;
        return likes - dislikes;
    }

    public Long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user_id;
    }

    public void setUser(User user_id) {
        this.user_id = user_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }


    public static final Comparator<Answer> SORT_IN_DESCENDING_ORDER = (o1, o2) -> {
        return Integer.compare(0, o1.getTime().compareTo(o2.getTime()));
    };


    public static final Comparator<Answer> SORT_BY_RATING = (o1, o2) -> {
        int result=0;
        if (o1.getRating() == o2.getRating()) result = 0;
        if (o1.getRating() < o2.getRating()) result = 1;
        if (o1.getRating() > o2.getRating()) result = -1;
        return result;
    };
}

