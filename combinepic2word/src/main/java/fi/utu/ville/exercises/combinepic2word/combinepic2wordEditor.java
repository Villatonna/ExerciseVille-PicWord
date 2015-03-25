package fi.utu.ville.exercises.combinepic2word;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
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

public class combinepic2wordEditor extends VilleContent implements
        Editor<combinepic2wordExerciseData> {

    /**
     *
     */
    private static final long serialVersionUID = -4600841604409240872L;

    private static final int MAX_FILE_SIZE_KB = 1024;

    private static final String MIME_FILTER = "^image/.*$";

    private EditorHelper<combinepic2wordExerciseData> editorHelper;

    private TextField questionText1;
    private TextField questionText2;
    private TextField questionText3;
    private TextField questionText4;
    private TextField questionText5;
    private TextField questionText6;
    private TextField questionText7;
    private TextField questionText8;
    private TextField questionTextHoax;

    private AbstractFile currImgFile1=null;
    private AbstractFile currImgFile2=null;
    private AbstractFile currImgFile3=null;
    private AbstractFile currImgFile4=null;
    private AbstractFile currImgFile5=null;
    private AbstractFile currImgFile6=null;
    private AbstractFile currImgFile7=null;
    private AbstractFile currImgFile8=null;

    private Image img;

    private ArrayList<picwordpair> oldPairs;

    private ArrayList<picwordpair> newPairs;

    private ArrayList<hoaxWord> oldHoaxWords;

    private ArrayList<hoaxWord> newHoaxWords;

    private Localizer localizer;

    private AbstractEditorLayout layout;

    Select select;

    Select selecthoax;

    Button deleteButton;

    Button addWordButton;

    Button deleteHoaxButton;

    SimpleFileUploader uploader1;
    SimpleFileUploader uploader2;
    SimpleFileUploader uploader3;
    SimpleFileUploader uploader4;
    SimpleFileUploader uploader5;
    SimpleFileUploader uploader6;
    SimpleFileUploader uploader7;
    SimpleFileUploader uploader8;

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

        selecthoax = new Select("Pairless words");
        selecthoax.setNullSelectionAllowed(false);
        selecthoax.addContainerProperty("list", hoaxWord.class, null);

        deleteButton = new Button("Remove Picture-Word pair");
        addWordButton = new Button("Add pairless word");
        deleteHoaxButton = new Button("Remove pairless word");
        if (oldData==null){
            oldData = new combinepic2wordExerciseData();
        }
        doLayout(oldData);

    }

    private combinepic2wordExerciseData getCurrentExercise() {
        return new combinepic2wordExerciseData(newPairs, newHoaxWords);
    }

    @Override
    public boolean isOkToExit() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void doLayout() {

    }

    public void doLayout(combinepic2wordExerciseData oldData) {

        oldPairs = oldData.getExerciseData();
        oldHoaxWords = oldData.getHoaxWords();
        newPairs=oldPairs;
        newHoaxWords=oldHoaxWords;
        System.out.println("Size of newPairs " + newPairs.size());
        System.out.println("Size of oldPairs " + oldPairs.size());
        if(newPairs!=null){
            for(int i=0;i<newPairs.size();i++){
              System.out.println("newPairs content " + newPairs.get(i).getAnswer());
            }
        }
        else{
            System.out.println("newPairs was null");
        }
        
        
        
        this.setMargin(false);
        this.setSpacing(false);
        this.setWidth("100%");

        layout = StandardUIFactory.getTwoColumnView();
        addComponent(layout);

        layout.setTitle("Editor");

        
        layout.addToLeft(editorHelper.getInfoEditorView());

        layout.addToTop(editorHelper
                .getControlbar(new EditedExerciseGiver<combinepic2wordExerciseData>() {

                    @Override
                    public combinepic2wordExerciseData getCurrExerData(
                            boolean forSaving) {
                                return getCurrentExercise();
                            }
                }));

        GridLayout editlayout = new GridLayout(8, 12);
        editlayout.setRowExpandRatio(3, 1);
        
        doUpdateList();

        Label questionTextCapt = new Label(
                localizer.getUIText(combinepic2wordUiConstants.QUESTION));
        questionTextCapt.addStyleName(combinepic2wordThemeConsts.TITLE_STYLE);
        
        questionText1 = new TextField("Enter answer.");
        questionText1.setValue("");
        questionText1.setImmediate(true);

        questionText2 = new TextField("Enter answer.");
        questionText2.setValue("");
        questionText2.setImmediate(true);

        questionText3 = new TextField("Enter answer.");
        questionText3.setValue("");
        questionText3.setImmediate(true);

        questionText4 = new TextField("Enter answer.");
        questionText4.setValue("");
        questionText4.setImmediate(true);

        questionText5 = new TextField("Enter answer.");
        questionText5.setValue("");
        questionText5.setImmediate(true);

        questionText6 = new TextField("Enter answer.");
        questionText6.setValue("");
        questionText6.setImmediate(true);

        questionText7 = new TextField("Enter answer.");
        questionText7.setValue("");
        questionText7.setImmediate(true);

        questionText8 = new TextField("Enter answer.");
        questionText8.setValue("");
        questionText8.setImmediate(true);
        
        questionTextHoax = new TextField("Enter incorrect answer option.");
        questionTextHoax.setValue("");
        questionTextHoax.setImmediate(true);

        uploader1 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);

        uploader2 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);

        uploader3 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);

        uploader4 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);

        uploader5 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);

        uploader6 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);

        uploader7 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);

        uploader8 = new SimpleFileUploader(localizer,
                editorHelper.getTempManager(), MAX_FILE_SIZE_KB,
                MIME_FILTER);
        
        updateFields();

        uploader1.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
                if(questionText1.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
                else{
                    currImgFile1 = new AFFile(tempFile);
                    setImageWordPair(questionText1.getValue(), currImgFile1,1); 
                    
                }

            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile1 = null;
            }
        });

        uploader2.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
                if(questionText2.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
                else{
                currImgFile2 = new AFFile(tempFile);
                setImageWordPair(questionText2.getValue(), currImgFile2,2);
                }
            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile2 = null;
            }
        });

        uploader3.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
                if(questionText3.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
                else{
                    currImgFile3 = new AFFile(tempFile);
                    setImageWordPair(questionText3.getValue(), currImgFile3,3);
                }
            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile3 = null;
            }
        });

        uploader4.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
            if(questionText4.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
            else{
                    currImgFile4 = new AFFile(tempFile);
                    setImageWordPair(questionText4.getValue(), currImgFile4,4);
                }
            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile4 = null;
            }
        });

        uploader5.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
            if(questionText5.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
            else{
                currImgFile5 = new AFFile(tempFile);
                setImageWordPair(questionText5.getValue(), currImgFile5,5);
            }
            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile5 = null;
            }
        });

        uploader6.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
                if(questionText6.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
                else{
                   currImgFile6 = new AFFile(tempFile);
                   setImageWordPair(questionText6.getValue(), currImgFile6,6);
                }
            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile6 = null;
            }
        });

        uploader7.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
                if(questionText7.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
                else{
                    currImgFile7 = new AFFile(tempFile);
                    setImageWordPair(questionText7.getValue(), currImgFile7,7);
                }
            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile7 = null;
            }
        });

        uploader8.registerUploaderListener(new UploaderListener() {

            @Override
            public void fileUploadSucceeded(File tempFile, String fileName, String mimeType) {
                if(questionText8.getValue().equals("")){
                    Notification.show(
                    "Give correct answer!",
                    Notification.TYPE_WARNING_MESSAGE);                   
                }
                else{
                currImgFile8 = new AFFile(tempFile);
                setImageWordPair(questionText8.getValue(), currImgFile8, 8);
                }
            }

            @Override
            public void uploadedFileDeleted(File tempFile) {
                currImgFile8 = null;
            }
        });

        addWordButton.setId("addword");
        deleteHoaxButton.setId("deletehoax");

        deleteHoaxButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String removed = selecthoax.getItemCaption(selecthoax.getValue());
                for (int i = 0; i < newHoaxWords.size(); i++) {
                    if (newHoaxWords.get(i).getHoaxAnswer().equals(removed)) {
                        newHoaxWords.remove(i);
                        break;
                    }
                else{
                        //Empty selection
                    }
                }
                doUpdateList();
            }
        });

        addWordButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(questionTextHoax.getValue().equals("")){
                    Notification.show(
                    "Empty value not acceptable!",
                    Notification.TYPE_WARNING_MESSAGE);
                }
                else{
                    newHoaxWords.add(new hoaxWord(questionTextHoax.getValue()));
                    questionTextHoax.setValue("");
                    doUpdateList();
                }
            }
        });

        editlayout.addComponent(questionText1, 0, 0);
        editlayout.addComponent(questionText2, 0, 1);
        editlayout.addComponent(questionText3, 0, 2);
        editlayout.addComponent(questionText4, 0, 3);
        editlayout.addComponent(questionText5, 0, 4);
        editlayout.addComponent(questionText6, 0, 5);
        editlayout.addComponent(questionText7, 0, 6);
        editlayout.addComponent(questionText8, 0, 7);
        editlayout.addComponent(uploader1, 1, 0);
        editlayout.addComponent(uploader2, 1, 1);
        editlayout.addComponent(uploader3, 1, 2);
        editlayout.addComponent(uploader4, 1, 3);
        editlayout.addComponent(uploader5, 1, 4);
        editlayout.addComponent(uploader6, 1, 5);
        editlayout.addComponent(uploader7, 1, 6);
        editlayout.addComponent(uploader8, 1, 7);
        editlayout.addComponent(questionTextHoax, 0, 8);
        editlayout.addComponent(addWordButton, 0, 9);
        editlayout.addComponent(selecthoax, 1, 8);
        editlayout.addComponent(deleteHoaxButton, 1, 9);

        layout.addToRight(editlayout);
    }

    @Override
    public String getViewName() {
        return "StubExercise";
    }

    private void doUpdateList() {
        selecthoax.removeAllItems();
        for (int i = 0; i < newHoaxWords.size(); i++) {
            Object itemId = selecthoax.addItem(newHoaxWords.get(i).getHoaxAnswer());
        }
    }

    private void setImageWordPair(String str, AbstractFile file, int pos) {
        newPairs.set(pos-1, new picwordpair(str,file,pos));
        
    }

    private void updateFields() {
        questionText1.setValue(newPairs.get(0).getAnswer());
        questionText2.setValue(newPairs.get(1).getAnswer());
        questionText3.setValue(newPairs.get(2).getAnswer());
        questionText4.setValue(newPairs.get(3).getAnswer());
        questionText5.setValue(newPairs.get(4).getAnswer());
        questionText6.setValue(newPairs.get(5).getAnswer());
        questionText7.setValue(newPairs.get(6).getAnswer());
        questionText8.setValue(newPairs.get(7).getAnswer());
        
        currImgFile1=newPairs.get(0).getFile();
        currImgFile2=newPairs.get(1).getFile();
        currImgFile3=newPairs.get(2).getFile();
        currImgFile4=newPairs.get(3).getFile();
        currImgFile5=newPairs.get(4).getFile();
        currImgFile6=newPairs.get(5).getFile();
        currImgFile7=newPairs.get(6).getFile();
        currImgFile8=newPairs.get(7).getFile();
        
        if (currImgFile1 != null) {
            uploader1.setAbstractUploadedFile(currImgFile1);
        }
        if (currImgFile2 != null) {
            uploader2.setAbstractUploadedFile(currImgFile2);
        }
        if (currImgFile3 != null) {
            uploader3.setAbstractUploadedFile(currImgFile3);
        }
        if (currImgFile4 != null) {
            uploader4.setAbstractUploadedFile(currImgFile4);
        }   
        if (currImgFile5 != null) {
            uploader5.setAbstractUploadedFile(currImgFile5);
        }
        if (currImgFile6 != null) {
            uploader6.setAbstractUploadedFile(currImgFile6);
        }
        
        if (currImgFile7 != null) {
            uploader7.setAbstractUploadedFile(currImgFile7);
        }       
        if (currImgFile8 != null) {
            uploader8.setAbstractUploadedFile(currImgFile8);
        }
    }
}
