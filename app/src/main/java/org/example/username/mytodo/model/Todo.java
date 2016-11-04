package org.example.username.mytodo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hiroaki on 2016/10/30.
 */

public class Todo extends RealmObject {
    @PrimaryKey
    private long id;
    private long categoryId;
    private String title;
    private boolean done;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
