    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.utu.ville.exercises.combinepic2word;

class hoaxWord {
    private String hoaxAnswer;
    private Object item;
    
    public hoaxWord(String hoax){
        hoaxAnswer=hoax;
    }
    
    public void setItemObject(Object i){
        item=i;
    }
    
    public Object getItemObject(){
        return item;
    }
    
    public Object getItem(){
        return item;
    }
    
    public void setHoaxAnswer(String hoax){
        hoaxAnswer=hoax;
    }
    
    public String getHoaxAnswer(){
        return hoaxAnswer;
    }
}
