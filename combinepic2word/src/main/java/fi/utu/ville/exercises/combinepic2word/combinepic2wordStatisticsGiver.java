package fi.utu.ville.exercises.combinepic2word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vaadin.ui.Component;

import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.StatisticalSubmissionInfo;
import fi.utu.ville.exercises.model.StatisticsInfoColumn;
import fi.utu.ville.exercises.model.SubmissionStatisticsGiver;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

public class combinepic2wordStatisticsGiver implements
	SubmissionStatisticsGiver<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> {

	/**
	* 
	*/
	private static final long serialVersionUID = -1410253605264134011L;
	
	private Localizer localizer;
	
	private List<StatisticsInfoColumn<?>> statCols;
	
	@Override
	public void initialize(
		combinepic2wordExerciseData exercise,
		List<StatisticalSubmissionInfo<combinepic2wordSubmissionInfo>> dataObjects,
		Localizer localizer, TempFilesManager tempManager)
		throws ExerciseException {
			this.localizer = localizer;
			
			initStatsCol(dataObjects, exercise);
	
	}
	
	private void initStatsCol(
		List<StatisticalSubmissionInfo<combinepic2wordSubmissionInfo>> data,
		combinepic2wordExerciseData exer) {
	
		List<String> answers = new ArrayList<String>();
		
		for (int i = 0, n = data.size(); i < n; i++) {
			answers.add(data.get(i).getSubmissionData().getAnswer());
		}
		
		StatisticsInfoColumn<String> answersCol = new StatisticsInfoColumn<String>(
				localizer.getUIText(combinepic2wordUiConstants.ANSWER),
				localizer.getUIText(combinepic2wordUiConstants.ANSWER_COL_DESC,
						exer.getQuestion()), String.class, answers, true);
		
		statCols = Collections
				.<StatisticsInfoColumn<?>> singletonList(answersCol);
	}
	
	@Override
	public Component getView() {
		// no extra statistics functionality, just export the data as
		// stat-column
		return null;
	}
	
	@Override
	public List<StatisticsInfoColumn<?>> getAsTabularData() {
		return statCols;
	}

}
