package Board;

public class Question {

	int questionCode;
	String buyerId;
	String title;
	String questionContents;
	String writeDate;

	@Override
	public String toString() {
		return "board [questionCode=" + questionCode + ", buyerId=" + buyerId + ", title=" + title
				+ ", questionContents=" + questionContents + ", writeDate=" + writeDate + ", + ]";
	}

	public int getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(int questionCode) {
		this.questionCode = questionCode;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestionContents() {
		return questionContents;
	}

	public void setQuestionContents(String questionContents) {
		this.questionContents = questionContents;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public Question() {

	}

	public Question(int questionCode, String buyerId, String title, String questionContents, String writeDate) {
		super();
		this.questionCode = questionCode;
		this.buyerId = buyerId;
		this.title = title;
		this.questionContents = questionContents;
		this.writeDate = writeDate;
	}

}
