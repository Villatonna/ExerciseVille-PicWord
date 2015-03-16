package fi.utu.ville.exercises.combinepic2word;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.SubmissionVisualizer;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

public class combinepic2wordSubmissionViewer extends VerticalLayout implements
		SubmissionVisualizer<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6260031633710031462L;
	private combinepic2wordExerciseData exer;
	private combinepic2wordSubmissionInfo submInfo;

	private Localizer localizer;
	
	public combinepic2wordSubmissionViewer() {
	}

	@Override
	public void initialize(combinepic2wordExerciseData exercise,
			combinepic2wordSubmissionInfo dataObject, Localizer localizer,
			TempFilesManager tempManager) throws ExerciseException {
		this.localizer = localizer;
		this.exer = exercise;
		this.submInfo = dataObject;
		doLayout();
	}

	private void doLayout() {
		this.addComponent(new Label(localizer.getUIText(combinepic2wordUiConstants.QUESTION) + 
				": " + exer.getQuestion()));
		Label answ = new Label(localizer.getUIText(combinepic2wordUiConstants.ANSWER) + 
				": "  + submInfo.getAnswer());
		answ.addStyleName(combinepic2wordThemeConsts.ANSWER_STYLE);
		this.addComponent(answ);
	}

	@Override
	public Component getView() {
		return this;
	}

	@Override
	public String exportSubmissionDataAsText() {
		return localizer.getUIText(combinepic2wordUiConstants.QUESTION, "\n", 
				exer.getQuestion(), submInfo.getAnswer());
		
	}

}
