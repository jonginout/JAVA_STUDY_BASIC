package snippet;

public class Snippet {
	update t17_board
		set title = #{title},
		content = #{content},
		writer = #{writer}
	where no = #{no}
}

