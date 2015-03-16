package fi.utu.ville.exercises.combinepic2word;

import fi.utu.ville.exercises.model.SubmissionInfo;

public class combinepic2wordSubmissionInfo implements SubmissionInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702870727095225372L;

	private final String answer;

	public combinepic2wordSubmissionInfo(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

}
