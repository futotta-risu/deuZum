package io.github.fatsquirrels.deuzum.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Tested {
	boolean tested();

}
