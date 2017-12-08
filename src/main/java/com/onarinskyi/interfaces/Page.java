package com.onarinskyi.interfaces;

import com.onarinskyi.reflection.Reflection;

public interface Page {
    void open();
    default void instantiate() {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
    }
}
