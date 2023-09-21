package board;

public class Question {

	int num;
	int questionCode;
	String buyerId;
	String title;
	String quetionContents;
	String writeDate;

	public Question() {
		// TODO Auto-generated constructor stub
	}

	
	public Question(int num, int questionCode, String buyerId, String title, String quetionContents, String writeDate) {
		super();
		this.num = num;
		this.questionCode = questionCode;
		this.buyerId = buyerId;
		this.title = title;
		this.quetionContents = quetionContents;
		this.writeDate = writeDate;
	}


	public Question(int questionCode, String buyerId, String title, String quetionContents, String writeDate) {
		super();
		this.questionCode = questionCode;
		this.buyerId = buyerId;
		this.title = title;
		this.quetionContents = quetionContents;
		this.writeDate = writeDate;
	}

	
	
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
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

	public String getQuetionContents() {
		return quetionContents;
	}

	public void setQuetionContents(String quetionContents) {
		this.quetionContents = quetionContents;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "Question [questionCode=" + questionCode + ", buyerId=" + buyerId + ", title=" + title
				+ ", quetionContents=" + quetionContents + ", writeDate=" + writeDate + "]";
	}
	
	
}

