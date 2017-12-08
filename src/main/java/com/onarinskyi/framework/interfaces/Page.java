package com.onarinskyi.framework.interfaces;

import com.onarinskyi.framework.reflection.Reflection;

public interface Page {
    void open();
    default void instantiate() {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
    }
}
