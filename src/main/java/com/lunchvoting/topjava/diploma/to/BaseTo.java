package com.lunchvoting.topjava.diploma.to;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunchvoting.topjava.diploma.HasId;
import com.lunchvoting.topjava.diploma.View;

public abstract class BaseTo implements HasId {

    @JsonView(View.JsonREST.class)
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
