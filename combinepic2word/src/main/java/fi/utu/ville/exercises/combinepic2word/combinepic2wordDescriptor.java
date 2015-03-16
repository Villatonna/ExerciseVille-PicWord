package fi.utu.ville.exercises.combinepic2word;

import com.vaadin.server.Resource;

import fi.utu.ville.exercises.helpers.GsonPersistenceHandler;
import fi.utu.ville.exercises.model.ExerciseTypeDescriptor;
import fi.utu.ville.exercises.model.PersistenceHandler;
import fi.utu.ville.exercises.model.SubmissionStatisticsGiver;
import fi.utu.ville.exercises.model.SubmissionVisualizer;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.StandardIcon;
import fi.utu.ville.standardutils.StandardIcon.IconVariant;

public class combinepic2wordDescriptor implements
		ExerciseTypeDescriptor<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743225101617556960L;

	public static final combinepic2wordDescriptor INSTANCE = new combinepic2wordDescriptor();

	private combinepic2wordDescriptor() {

	}

	@Override
	public PersistenceHandler<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> newExerciseXML() {
		// You can also implement your own PersistenceHandler if you want (see JavaDoc for more info)
		return new GsonPersistenceHandler<combinepic2wordExerciseData, combinepic2wordSubmissionInfo>(
				getTypeDataClass(), getSubDataClass());
	}

	@Override
	public combinepic2wordExecutor newExerciseExecutor() {
		return new combinepic2wordExecutor();
	}

	@Override
	public combinepic2wordEditor newExerciseEditor() {
		return new combinepic2wordEditor();
	}

	@Override
	public Class<combinepic2wordExerciseData> getTypeDataClass() {
		return combinepic2wordExerciseData.class;
	}

	@Override
	public Class<combinepic2wordSubmissionInfo> getSubDataClass() {
		return combinepic2wordSubmissionInfo.class;
	}

	@Override
	public SubmissionStatisticsGiver<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> newStatisticsGiver() {
		return new combinepic2wordStatisticsGiver();
	}

	@Override
	public SubmissionVisualizer<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> newSubmissionVisualizer() {
		return new combinepic2wordSubmissionViewer();
	}

	@Override
	public String getTypeName(Localizer localizer) {
		return localizer.getUIText(combinepic2wordUiConstants.NAME);
	}

	@Override
	public String getTypeDescription(Localizer localizer) {
		return localizer.getUIText(combinepic2wordUiConstants.DESC);
	}

	@Override
	public Resource getSmallTypeIcon() {
		return combinepic2wordIcon.SMALL_TYPE_ICON.getIcon();
	}

	@Override
	public Resource getMediumTypeIcon() {
		return combinepic2wordIcon.SMALL_TYPE_ICON.getIcon();
	}

	@Override
	public Resource getLargeTypeIcon() {
		return combinepic2wordIcon.SMALL_TYPE_ICON.getIcon();
	}
	
	@Override
	public String getHTMLIcon(){
		return StandardIcon.RawIcon.dribbble.variant(IconVariant.ORANGE);
	}
	
	@Override
	public boolean isManuallyGraded() {
		return false;
	}

}