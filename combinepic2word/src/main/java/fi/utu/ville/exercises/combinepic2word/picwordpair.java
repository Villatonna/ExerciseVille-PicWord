/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.utu.ville.exercises.combinepic2word;

import com.vaadin.ui.Image;
import fi.utu.ville.standardutils.AbstractFile;


/**
 *
 * @author jarde
 */
class picwordpair {
    private String answer;
    private AbstractFile file;
    private Object item;
    private int exercisePosition;
    
    public picwordpair(String ans, AbstractFile file, int pos){
        this.answer = ans;
        this.file = file;
        this.exercisePosition = pos;     
    }
    
    public picwordpair(){
        this.answer="";
        this.file=null;
        this.exercisePosition=-1;
    }
    
    public String getAnswer(){
        return this.answer;
    }
    
    public AbstractFile getFile(){
        return this.file;
    }
    
    public void setItemObject(Object obj){
        item=obj;
    }
    
    public Object getItemObject(){
        return item;
    }
    
    public void setPosition(int pos){
        exercisePosition=pos;
    }
    
    public Integer getPosition(){
        return exercisePosition;
    }
}
