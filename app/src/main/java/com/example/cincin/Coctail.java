package com.example.cincin;

public class Coctail {
    private String nameId;
    private String name;
    private String category;
    private String glass;
    private String instructions;


    public Coctail(String nameId, String name, String category, String glass, String instructions){
        this.nameId = nameId;
        this.name = name;
        this.category = category;
        this.glass = glass;
        this.instructions = instructions;
    }

    public String getNameId() {
        return nameId;
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

    public void setNameId(String nameId) {
        this.nameId = nameId;
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

    @Override
    public String toString() {
        return "Coctail{" +
                "nameId='" + nameId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", glass=" + glass +
                ", instructions=" + instructions +
                '}';
    }

}
