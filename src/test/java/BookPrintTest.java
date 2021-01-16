import com.muyu.utils.BookParseUtil;

public class BookPrintTest {
	public static void main(String[] args) {
		BookParseUtil bookParseUtil = new BookParseUtil();
		bookParseUtil.printTxt(bookParseUtil.obtainBook());
	}
}
