package anno.quiz;

public class BoardController {
	@RequestMapping(value="/board/list") // value= 생략가능
	public void list() {
		System.out.println("목록 조회용");
	}
	@RequestMapping("/board/writeForm")
	public void writeForm() {
		System.out.println("글쓰기 폼용");
	}
	@RequestMapping("/board/delete")
	public void delete() {
		System.out.println("글삭제용");
	}
}


