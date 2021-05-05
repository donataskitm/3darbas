package com.example.cincin;

public class Coctail {
    private String name;
    private String category;
    private String glass;
    private String instructions;
    private String imgLink;


    public Coctail(String name, String category, String glass, String instructions, String imgLink){

        this.name = name;
        this.category = category;
        this.glass = glass;
        this.instructions = instructions;
        this.imgLink = imgLink;

    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getGlass() {
        return glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImgLink() {
        return imgLink;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    @Override
    public String toString() {
        return "Coctail{" +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", glass=" + glass +
                ", instructions=" + instructions +
                ", link=" + imgLink +
                '}';
    }

}
