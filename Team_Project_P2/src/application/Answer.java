package application;

public class Answer {
	private String text;
	private String user;
	private boolean solution;

	public Answer(String text, String user) {
		this.text = text;
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public String getUser() {
		return user;
	}

	public void update(String newQuestion) {
		if (user.equals(UserLoginPage.userNamePublic)) {
			text = newQuestion;
		}
	}
	
	public void setSolution(boolean solution) {
		this.solution = solution;
	}
	
	@Override
	public String toString() {
		if (solution) {
			return text + " (Posted by: " + user + ") (Solution!)";
		}
		return text + " (Posted by: " + user + ")";
	}
}
