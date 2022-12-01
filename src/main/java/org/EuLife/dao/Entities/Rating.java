package org.EuLife.dao.Entities;

import org.EuLife.dao.enums.TypeOfLike;

import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Answer answer;

    @Enumerated(EnumType.STRING)
    private TypeOfLike Type;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }


    public TypeOfLike getType() {
        return Type;
    }

    public void setType(TypeOfLike type) {
        Type = type;
    }
}
