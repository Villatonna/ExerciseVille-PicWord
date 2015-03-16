package fi.utu.ville.exercises.combinepic2word;

import fi.utu.ville.exercises.model.ExerciseData;
import fi.utu.ville.standardutils.AbstractFile;
import java.util.ArrayList;

public class combinepic2wordExerciseData implements ExerciseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716445297446246493L;

	//private final String question;
	//private final AbstractFile imgFile;
        private ArrayList<picwordpair> picwordList;

        public combinepic2wordExerciseData(){
            picwordList = new ArrayList();
        }
        
	public combinepic2wordExerciseData(ArrayList a) {
		picwordList = new ArrayList();
                this.picwordList = a;
	}
        
        public ArrayList getExerciseData(){
            return this.picwordList;
        }

}
