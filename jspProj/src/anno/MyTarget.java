package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/*
 // @Target를 설정하지 않는다면 모든 위치에서 사용이 가능하다.
@Target({
	ElementType.PARAMETER, ElementType.TYPE, ElementType.METHOD,
	ElementType.FIELD
})
*/
public @interface MyTarget {}










