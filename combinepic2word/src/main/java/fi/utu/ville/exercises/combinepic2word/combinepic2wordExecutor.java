package fi.utu.ville.exercises.combinepic2word;

import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fi.utu.ville.exercises.helpers.ExerciseExecutionHelper;
import fi.utu.ville.exercises.model.ExecutionSettings;
import fi.utu.ville.exercises.model.ExecutionState;
import fi.utu.ville.exercises.model.ExecutionStateChangeListener;
import fi.utu.ville.exercises.model.Executor;
import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.SubmissionListener;
import fi.utu.ville.exercises.model.SubmissionType;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

public class combinepic2wordExecutor extends VerticalLayout implements
		Executor<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682119786422750060L;

	private final ExerciseExecutionHelper<combinepic2wordSubmissionInfo> execHelper =

	new ExerciseExecutionHelper<combinepic2wordSubmissionInfo>();

	private final TextField answerField = new TextField();

	public combinepic2wordExecutor() {

	}

	@Override
	public void initialize(Localizer localizer,
			combinepic2wordExerciseData exerciseData, combinepic2wordSubmissionInfo oldSubm,
			TempFilesManager materials, ExecutionSettings fbSettings)
			throws ExerciseException {
		answerField.setCaption(localizer.getUIText(combinepic2wordUiConstants.ANSWER));
		doLayout(exerciseData, oldSubm != null ? oldSubm.getAnswer() : "");
	}

	private void doLayout(combinepic2wordExerciseData exerciseData, String oldAnswer) {
		if (exerciseData.getImgFile() != null) {
			this.addComponent(new Image(null, exerciseData.getImgFile()
					.getAsResource()));
		}
		this.addComponent(new Label(exerciseData.getQuestion()));
		answerField.setValue(oldAnswer);
		this.addComponent(answerField);
	}

	@Override
	public void registerSubmitListener(
			SubmissionListener<combinepic2wordSubmissionInfo> submitListener) {
		execHelper.registerSubmitListener(submitListener);
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void shutdown() {
		// nothing to do here
	}

	@Override
	public void askReset() {
		// nothing to do here
	}

	@Override
	public ExecutionState getCurrentExecutionState() {
		return execHelper.getState();
	}

	@Override
	public void askSubmit(SubmissionType submType) {
		double corr = 1.0;

		String answer = answerField.getValue();
		execHelper.informOnlySubmit(corr, new combinepic2wordSubmissionInfo(answer),
				submType, null);

	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

}
