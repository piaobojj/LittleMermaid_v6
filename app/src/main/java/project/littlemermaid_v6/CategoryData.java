package project.littlemermaid_v6;


import java.io.Serializable;

public class CategoryData implements Serializable {

    public String lemma;
    public String NGram;

    public int Education;
    public int History;
    public int Misc;

    /*
    public int ACADEMIC;
    public Double DISPERSION;
    public int FICTION;
    public int NEWSPAPER;
    public int POP_MAG;
    public int rank;
*/

    public CategoryData(String lemma, String NGram, int Education, int History, int Misc){

        this.lemma = lemma;
        this.NGram = NGram;
        this.Education = Education;
        this.History = History;
        this.Misc = Misc;
        /*
        this.ACADEMIC = ACADEMIC;
        this.DISPERSION = DISPERSION;
        this.FICTION = FICTION;
        this.NEWSPAPER = NEWSPAPER;
        this.POP_MAG = POP_MAG;
        this.rank = rank;
*/
    }

    public String getLemma(){
        return lemma;
    }

    public String getNGram(){
        return NGram;
    }

    public int getEducation(){
        return Education;
    }

    public String toString(){
        return  " (" + getLemma() + "," + getEducation() + ")";
    }


}
