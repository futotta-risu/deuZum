package io.github.fatsquirrels.deuzum.utils.meta.anotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Tested {
	boolean tested() default true;
}

