import java.util.HashMap;

public class Question {

	private String question;
	private HashMap<String, Object> responses = new HashMap<>();

	public Question(String question) {
		this.question = question;
	}

	public void addResponse(String answer, Object response) {
		responses.put(answer, response);
	}

	public String getQuestion() {
		return question;
	}

	public String getResponse(String answer){
		//todo not a fan of this casting
		return (String)responses.get(answer);
	}

}
//interaction starts with a question
//answers may prompt
	//further interactions
	//more information
	//if not sensible, repeat the question