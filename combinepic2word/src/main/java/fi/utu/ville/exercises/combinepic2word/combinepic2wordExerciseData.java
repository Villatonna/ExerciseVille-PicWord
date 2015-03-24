package fi.utu.ville.exercises.combinepic2word;

import fi.utu.ville.exercises.model.ExerciseData;
import java.util.ArrayList;

public class combinepic2wordExerciseData implements ExerciseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716445297446246493L;

	//private final String question;
	//private final AbstractFile imgFile;
        private ArrayList<picwordpair> picwordList;
        private ArrayList<hoaxWord> hoaxWordList;

        public combinepic2wordExerciseData(){
            this.picwordList = new ArrayList<picwordpair>();
            this.hoaxWordList = new ArrayList();
            for(int i=0;i<10;i++){
                this.picwordList.add(i,new picwordpair());
            }
        }
        
	public combinepic2wordExerciseData(ArrayList a) {
		picwordList = new ArrayList();
                hoaxWordList = new ArrayList();
                this.picwordList = a;
	}
        
        public combinepic2wordExerciseData(ArrayList a, ArrayList b) {
		picwordList = new ArrayList();
                hoaxWordList = new ArrayList();
                this.picwordList = a;
                this.hoaxWordList = b;
	}
        
        public ArrayList getExerciseData(){
            return this.picwordList;
        }
        
        public void addHoaxWord(hoaxWord hoax){
            hoaxWordList.add(hoax);
        }
        
        public ArrayList getHoaxWords(){
            return hoaxWordList;
        }
        
        public String getQuestion(){
            String retval = "Asked pictures: ";
            for(int i=0; i<8;i++){
                retval=retval + " " + picwordList.get(i).getAnswer();
            }
            return retval;  
        }

}
