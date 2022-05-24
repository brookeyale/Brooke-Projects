public abstract class Clothes {
    String name;
    String clothingType;
    public String getName() {return name;}
    public void setName(String newName) {name = newName;}
    public void setClothingType(String newClothingType) {clothingType = newClothingType;}
    abstract void makeClothing();


    public String toString(){
        return name + "is/ are your " + clothingType + "for the day.";
    }
}
abstract class Shirt extends Clothes{
    String name;
    String clothingType;
    public Shirt(){}
    void makeClothing(){
        clothingType = "Shirt";
    }
}
abstract class Pants extends Clothes{
    String name;
    String clothingType;
    public Pants(){}
    void makeClothing(){
        clothingType = "Pants";
    }
}
abstract class Shoes extends Clothes{
    String name;
    String clothingType;
    public Shoes(){}
    void makeClothing(){
        clothingType = "Shoes";
    }
}
abstract class Jacket extends Clothes{
    String name;
    String clothingType;
    public Jacket(){}
    void makeClothing(){
        clothingType = "Jacket";
    }
}
abstract class Socks extends Clothes{
    String name;
    String clothingType;
    public Socks(){}
    void makeClothing(){
        clothingType = "Socks";
    }
}
abstract class Underwear extends Clothes{
    String name;
    String clothingType;
    public Underwear(){}
    void makeClothing(){
        clothingType = "Underwear";
    }
}
abstract class Hat extends Clothes{
    String name;
    String clothingType;
    public Hat(){}
    void makeClothing(){
        clothingType = "Hat";
    }
}
abstract class Belt extends Clothes{
    String name;
    String clothingType;
    public Belt(){}
    void makeClothing(){
        clothingType = "Belt";
    }
}
