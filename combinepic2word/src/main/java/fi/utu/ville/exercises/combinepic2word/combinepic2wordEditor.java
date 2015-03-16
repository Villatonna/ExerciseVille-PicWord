package fi.utu.ville.exercises.combinepic2word;

import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import java.io.File;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Select;

import fi.utu.ville.exercises.model.Editor;
import fi.utu.ville.exercises.model.EditorHelper;
import fi.utu.ville.exercises.model.EditorHelper.EditedExerciseGiver;
import fi.utu.ville.standardutils.AFFile;
import fi.utu.ville.standardutils.AbstractFile;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.SimpleFileUploader;
import fi.utu.ville.standardutils.StandardUIFactory;
import fi.utu.ville.standardutils.SimpleFileUploader.UploaderListener;
import fi.utu.ville.standardutils.ui.AbstractEditorLayout;
import fi.utu.ville.exercises.model.VilleContent;
import fi.utu.ville.exercises.model.VilleUI;
import java.util.ArrayList;
import java.util.Hashtable;

public class combinepic2wordEditor extends VilleContent implements
		Editor<combinepic2wordExerciseData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4600841604409240872L;

	private static final int MAX_FILE_SIZE_KB = 1024;
	
	private static final String MIME_FILTER = "^image/.*$";
	
	private EditorHelper<combinepic2wordExerciseData> editorHelper;

	private TextField questionText;

	private AbstractFile currImgFile;
        
        private File dirtyFile;
        
        private Image img;
        
        private ArrayList<picwordpair> oldPairs;
        
        private ArrayList<picwordpair> newPairs;

	private Localizer localizer;
	
	private AbstractEditorLayout layout;
        
        Hashtable<Object, picwordpair> listedObjects = new Hashtable<Object, picwordpair>();

        Select select;
        
        Button deleteButton;
        
        SimpleFileUploader uploader;
        
	public combinepic2wordEditor() {
		super(null);
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void initialize(VilleUI ui, Localizer localizer, combinepic2wordExerciseData oldData,
			EditorHelper<combinepic2wordExerciseData> editorHelper) {
		this.init(ui);
		this.localizer = localizer;

		this.editorHelper = editorHelper;

		editorHelper.getTempManager().initialize();
                select = new Select ("Select something here");
                select.setNullSelectionAllowed(false);
                select.addContainerProperty("list", picwordpair.class, null);
        
                deleteButton = new Button("Remove Picture-Word pair");
                if(oldPairs==null)
                    oldData = new combinepic2wordExerciseData();
                newPairs = oldData.getExerciseData();
                
		doLayout(oldData);
                
                
	}
        
        private combinepic2wordExerciseData getCurrentExercise(){
            return new combinepic2wordExerciseData(newPairs);
        }

	@Override
	public boolean isOkToExit() {
		// TODO Auto-generated method stub
		return true;
	}
        
        private void updateList(){
            
        }
	
	@Override
	public void doLayout() {
		
	}
	
	public void doLayout(combinepic2wordExerciseData oldData) {
            
                if(oldData!=null){
                    oldPairs = oldData.getExerciseData();
                }
                else{
                    oldPairs = new ArrayList();
                }
		this.setMargin(false);
		this.setSpacing(false);
		this.setWidth("100%");

		layout = StandardUIFactory.getTwoColumnView();
		addComponent(layout);
		
		layout.setTitle("Editor");
		
/*		String oldQuestion;
		if (oldData != null) {
			oldQuestion = oldData.getQuestion();
			currImgFile = oldData.getImgFile();
		} else {
			oldQuestion = "";
			currImgFile = null;
		}
*/          

                
        	layout.addToLeft(editorHelper.getInfoEditorView());


		layout.addToTop(editorHelper
				.getControlbar(new EditedExerciseGiver<combinepic2wordExerciseData>() {

					@Override
					public combinepic2wordExerciseData getCurrExerData(
							boolean forSaving) {
						return getCurrentExercise();
					}
				}));


		VerticalLayout editlayout = new VerticalLayout();
                //Create list of image-word pairs in exercise
                doUpdateList(oldPairs);

                

		Label questionTextCapt = new Label(
				localizer.getUIText(combinepic2wordUiConstants.QUESTION));
		questionTextCapt.addStyleName(combinepic2wordThemeConsts.TITLE_STYLE);
		questionText = new TextField("Answer for given picture.");
                questionText.setValue("");
                questionText.setImmediate(true);

		SimpleFileUploader uploader = new SimpleFileUploader(localizer,
				editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
				MIME_FILTER);

		
                uploader.registerUploaderListener(new UploaderListener() {


                    private static final long serialVersionUID = 8266397773350713952L;

                    @Override
                    public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
                        currImgFile = new AFFile(tempFile);
                        img = new Image (null,currImgFile.getAsResource());
                        img.setHeight("48px");
                        setImageWordPair(img,questionText.getValue(),currImgFile);
                        dirtyFile = tempFile;
                    }


                    @Override
                    public void uploadedFileDeleted(File tempFile) {
                        currImgFile= null;
                    }
                    
                    private void setImageWordPair(Image img, String caption, AbstractFile file) {
                        picwordpair Picwordpair = new picwordpair(img,caption,file);
                        newPairs.add(Picwordpair);
                        clearFields();
                        doUpdateList(newPairs);
                    }
                        

                    private void clearFields() {
                        currImgFile=null;
                        questionText.setValue("");
                                                
                    } 
		});



                    deleteButton.setId("deletepair");
                    deleteButton.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            newPairs.remove(select.getItem(select.getValue()));
                            if(!select.removeItem(select.getValue())){
                                //Booboo something went wrong!
                            }
                        }
                    }); 

                editlayout.addComponent(questionText);
		editlayout.addComponent(uploader);
                editlayout.addComponent(select);
                editlayout.addComponent(deleteButton);


		layout.addToRight(editlayout);
	}
	
	@Override
	public String getViewName(){
		return "StubExercise";
	}

    private void doUpdateList(ArrayList<picwordpair> PairsList) {
                for(int i=0; i<PairsList.size();i++){
                    Object itemId= select.;
                    select.setItemCaption(itemId,PairsList.get(i).getAnswer());
                }
    }
    
    Container createSelectData() {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("caption", picwordpair.class, null);
        return container;
    }
}

