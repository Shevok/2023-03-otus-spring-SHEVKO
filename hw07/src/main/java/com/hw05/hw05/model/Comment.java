package com.hw05.hw05.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Book book;

    public Comment(String content, Book book) {
        this.content = content;
        this.book = book;
    }

    public Comment(long id, String content) {
        this.id = id;
        this.content = content;
    }


}
