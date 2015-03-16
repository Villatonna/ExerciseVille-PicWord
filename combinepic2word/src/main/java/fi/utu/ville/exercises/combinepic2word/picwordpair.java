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
    private Image image;
    private String answer;
    private AbstractFile file;
    public picwordpair(Image img, String ans, AbstractFile file){
        this.answer = ans;
        this.image = img;
        this.file = file;
    }
    
    public Image getImage(){
        return this.image;
    }
    
    public String getAnswer(){
        return this.answer;
    }
    
    public AbstractFile getFile(){
        return this.file;
    }
}
