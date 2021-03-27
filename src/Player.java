public class Player {

    private Room currentRoom;
    private Room startRoom;

    public Player (){


        this.currentRoom = currentRoom;
        startRoom = null;

    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
