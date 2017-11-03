package anno;

public @interface MyDouble {
	String type() default "a";
	String name() default "b";
	String value() default "c";
}
