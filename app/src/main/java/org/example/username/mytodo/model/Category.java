package org.example.username.mytodo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hiroaki on 2016/10/30.
 */

public class Category extends RealmObject {
    @PrimaryKey
    private long id;
    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
