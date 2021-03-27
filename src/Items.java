public class Items {

    private String name;
    private String description;
    private int quantityOnTheBag;
    private int quantityInTheRoom;
    private double weight;

    public Items (String name, String description, int quantityOnTheBag, int quantityInTheRoom, double weight) {

        setName(name);
        setDescription(description);
        setQuantityOnTheBag(quantityOnTheBag);
        setQuantityInTheRoom(quantityInTheRoom);
        setWeight(weight);

    }

    public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getDescription () {
            return description;
        }

        public void setDescription (String description){
            this.description = description;
        }

        public int getQuantityOnTheBag () {
            return quantityOnTheBag;
        }

        public void setQuantityOnTheBag ( int quantityOnTheBag){
            this.quantityOnTheBag = quantityOnTheBag;
        }

        public int getQuantityInTheRoom () {
            return quantityInTheRoom;
        }

        public void setQuantityInTheRoom ( int quantityInTheRoom){
            this.quantityInTheRoom = quantityInTheRoom;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight){
            this.weight = weight;
        }

}