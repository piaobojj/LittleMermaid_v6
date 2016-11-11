package project.littlemermaid_v6;


import java.io.Serializable;

public class CategoryData implements Serializable {

    public int MAGAa;
    public int MAGChild;
    public int MAGEntertain;
    public int MAGFinancial;

    public CategoryData(int MAGAa, int MAGChild, int MAGEntertain, int MAGFinancial){

        this.MAGAa = MAGAa;
        this.MAGChild = MAGChild;
        this.MAGEntertain = MAGEntertain;
        this.MAGFinancial = MAGFinancial;

    }

    public String toString(){
        return  " (" + MAGAa + " " + MAGChild + " " + MAGEntertain + " " + MAGFinancial + ")";
    }


}
