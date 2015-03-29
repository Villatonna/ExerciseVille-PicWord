package fi.utu.ville.exercises.combinepic2word;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Select;
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
import java.util.ArrayList;
import java.util.Collections;

public class combinepic2wordExecutor implements
		Executor<combinepic2wordExerciseData, combinepic2wordSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682119786422750060L;

	private final ExerciseExecutionHelper<combinepic2wordSubmissionInfo> execHelper =

	new ExerciseExecutionHelper<combinepic2wordSubmissionInfo>();

        private ArrayList answerOptionList;
        private ArrayList<String> answerList = new ArrayList(8);
        private ArrayList<String> correctAnswers;
        private ArrayList<Select> answerFields;
	//private final TextField answerField = new TextField();
        private Select question;
        GridLayout answerLayout;
        

	public combinepic2wordExecutor() {

	}

	@Override
	public void initialize(Localizer localizer,
			combinepic2wordExerciseData exerciseData, combinepic2wordSubmissionInfo oldSubm,
			TempFilesManager materials, ExecutionSettings fbSettings)
			throws ExerciseException {
		doLayout(exerciseData, oldSubm != null ? oldSubm.getAnswer() : "");
	}

	private void doLayout(combinepic2wordExerciseData exerciseData, String oldAnswer) {
            
                answerLayout = new GridLayout(8, 8);
                answerFields = new ArrayList(8);
                correctAnswers = getCorrectAnswers(exerciseData);
                Select answerOptions=getAnswerSelect(exerciseData);
                ArrayList<picwordpair> questions = exerciseData.getExerciseData();
                for(int i=0;i<8;i++){
                    questions.get(i).getAnswer();
                    Image tempImage;
                    if(questions.get(i).getFile()!= null){
                        tempImage = new Image(null,questions.get(i).getFile().getAsResource());
                    }
                    else{
                        tempImage = new Image();
                    }
                    Select tempSelect = getAnswerSelect(exerciseData);
                    //Next line doesn't work?
                    //tempSelect.setNullSelectionAllowed(false);
                    answerFields.add(tempSelect);
                    tempImage.setHeight("80px");
                    int x, r, c;
                    if(i<4){
                        x=0;
                        r=1;
                        c=i;
                    }
                    else{
                        x=2;
                        r=3;
                        c=i-4;
                    }
                    answerLayout.addComponent(tempSelect,c,r);
                    answerLayout.addComponent(tempImage,c,x);
                    }
            }

	@Override
	public void registerSubmitListener(
			SubmissionListener<combinepic2wordSubmissionInfo> submitListener) {
		execHelper.registerSubmitListener(submitListener);
	}

	@Override
	public Layout getView() {
		return answerLayout;
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
                //Max points 8/8
                int points=0;
                String answer="Answers:";
                ArrayList<String> answerList = new ArrayList();
                
                for(int i=0;i<answerFields.size();i++){
                    Select temp = answerFields.get(i);
                    answerList.add(temp.getItemCaption(temp.getValue()));
                }
                for(int i=0;i<answerList.size();i++){
                    if(answerList.get(i)==null)
                        answerList.set(i, "");
                    if(answerList.get(i).equals(correctAnswers.get(i)))
                            points++;
                    answer = answer + " " + answerList.get(i);
                    System.out.println(answerList.get(i));
                    System.out.println(correctAnswers.get(i));
                }
                
                corr=(double)points/8;
		
                System.out.println(answer);
		execHelper.informOnlySubmit(corr, new combinepic2wordSubmissionInfo(answer),
				submType, null);
	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

    private Select getAnswerSelect(combinepic2wordExerciseData exerciseData) {
        Select answerOptions = new Select();
        ArrayList<String> totalList = new ArrayList();
        ArrayList<picwordpair> picwordlist = exerciseData.getExerciseData();
        ArrayList<hoaxWord> hoaxWordList = exerciseData.getHoaxWords();
        for(int i=0;i<8;i++){
                totalList.add(picwordlist.get(i).getAnswer());
        }
        for(int i=0;i<hoaxWordList.size();i++){
            totalList.add(hoaxWordList.get(i).getHoaxAnswer());
        }
        Collections.shuffle(totalList);
        for(int i=0;i<totalList.size();i++){
            answerOptions.addItem(totalList.get(i));
        }
        return answerOptions;
        
    }

    private ArrayList<String> getCorrectAnswers(combinepic2wordExerciseData exerciseData) {
        ArrayList<String> retval = new ArrayList(8);
        ArrayList<picwordpair> temp = exerciseData.getExerciseData();
        for(int i=0;i<8;i++){
            retval.add(temp.get(i).getAnswer());
            if(retval.get(i)=="")
                retval.set(i, "");
        }
        return retval;
    }

}
