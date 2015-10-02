package com.h2ord.corp.api.model;

import java.io.Serializable;

/**
 * Created by chy on 14-9-25.
 */
public abstract class BaseObject implements Serializable {
    private static final long serialVersionUID = -7018051492221602377L;

    public abstract boolean equals(Object paramObject);
    public abstract int hashCode();
}
