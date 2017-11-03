package annoJongin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Documented
//@Documented 어노테이션이 지정된 대상에
//이 어노테이션의 존재를 표기하도록 지정.

@Inherited
// 자신이 만든 어노테이션이 사용되게 될 자바 요소를 지정할 수 있다.
// 아래처럼 만들면 메소드에서만 사용가능한 어노테이션을 정의할수있다.
//@Target({ElementType.METHOD})

// 이놈은 어노테이션 정의라고 알려준다.
public @interface MyAnnotation {

	// 요소들은 인터페이스에서의 메서드 정의와 유사하다.
	// 자바 기본요소들을 모두 사용할수있고 ,
	// 배열도 사용가능하다. (복잡객체사용은 불가능)

	String value() default ""; // 요소에 기본값 설정

	String name();

	int age();

	String[] newNames();
}


//아래와 같이 타겟을 정할수있다.
//ElementType.ANNOTATION_TYPE
//ElementType.CONSTRUCTOR
//ElementType.FIELD
//ElementType.LOCAL_VARIABLE
//ElementType.METHOD
//ElementType.PACKAGE
//ElementType.PARAMETER
//ElementType.TYPE
