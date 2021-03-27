/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Items items;


    /**
     * Create the game and initialise its internal map.
     */
    public Game() {

        createItems();
        createRooms();
        parser = new Parser();
        player = new Player();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room amazonia, caatinga, cerrado, pampa, pantanal, mataAtlantica;

        // create the rooms

        amazonia = new Room("outside the main entrance of the university");
        caatinga = new Room("in a lecture theater");
        cerrado = new Room("in the campus pub");
        pampa = new Room("in a computing lab");
        pantanal = new Room("in the computing admin office");
        mataAtlantica = new Room("blabalalba");

        // initialise room exits

        amazonia.setExit("east", caatinga);
        amazonia.setExit("south", pampa);
        amazonia.setExit("west", cerrado);

        caatinga.setExit("west", amazonia);

        cerrado.setExit("east", amazonia);

        pampa.setExit("north", amazonia);
        pampa.setExit("east", pantanal);

        pantanal.setExit("west", pampa);

        currentRoom = amazonia;  // start game outside
    }

    /**
     * Main play routine.  Loops until end of play.
     */

    private void createItems() {
        Items fern, montsera, bouganvillea, maranta, episcia, grumixama, pitanga, mandacaru, bertolonia, ambuí, seeds, water;

        // create the items

        fern = new Items("fern: ", "this sweetie have something called spores that spread all over the place only with the wind", 0, 3, 1.0);
        montsera = new Items("montsera: ", "maybe you could look for a new one here to put somewhere else", 0, 5, 1.0);
        bouganvillea = new Items("bouganvillea: ", "this one was discovered with the country but be carefull because there is some thorns", 0, 1, 1.0);
        maranta = new Items("maranta: ", "they close their leaves when the air is too dry", 0, 3, 1.0);
        episcia = new Items("episcia: ", "the leaves spread in the ground and create roofs by itself", 0, 3, 1.0);
        grumixama = new Items("grumixama: ", "this fruit is delicious, maybe you can eat them and throw the seeds", 0, 7, 0.4);
        pitanga = new Items("pitanga: ", "they are beautiful red fruits", 0, 10, 0.4);
        mandacaru = new Items("mandacaru: ", "careful with the thorns", 0, 7, 1.5);
        bertolonia = new Items("bertolonia: ", "look at these leaves! They enjoy the umidity", 0, 9, 0.7);
        ambuí = new Items("ambuí: ", "nice to take them when they are all yellow just like the idea we have of sun", 0, 13, 0.4);
        seeds = new Items("seeds: ", "now you eat the fruit you have them", 0, 0, 0.4);
        water = new Items("water: ", "use it to help some of our friends", 0, 23, 1.0);

    }
        public void play ()
        {
            player.getCurrentRoom();
            printWelcome();

            // Enter the main command loop.  Here we repeatedly read commands and
            // execute them until the game is over.

            boolean finished = false;
            while (!finished) {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
            System.out.println("Thank you for playing.  Good bye.");
        }

        /**
         * Print out the opening message for the player.
         */
        private void printWelcome ()
        {
            System.out.println();
            System.out.println("Welcome to the last chance to find a Little Trick to the Return!");
            System.out.println("You are walking around brazilian biomas and you may discover");
            System.out.println("if all plants are in the right places.");
            System.out.println("Besides this, you have the task to improve the level of oxygen");
            System.out.println(" by spreading and taking care of the plants you find in the way. ");
            System.out.println("But be careful and pay attention! Plants will tell what they need");
            System.out.println("and some of them need nothing but to be there exactly where they are.");
            System.out.println("Type 'help' if you need help.");
            System.out.println();
            System.out.println(currentRoom.getLongDescription());
        }

        /**
         * Given a command, process (that is: execute) the command.
         * @param command The command to be processed.
         * @return true If the command ends the game, false otherwise.
         */
        private boolean processCommand (Command command)
        {
            boolean wantToQuit = false;

            if (command.isUnknown()) {
                System.out.println("I don't know what you mean...");
                return false;
            }

            String commandWord = command.getCommandWord();
            if (commandWord.equals("help")) {
                printHelp();
            } else if (commandWord.equals("go")) {
                goRoom(command);
            } else if (commandWord.equals("look")) {
                goRoom(command);
            } else if (commandWord.equals("take")) {
                goRoom(command);
            } else if (commandWord.equals("put")) {
                goRoom(command);
            } else if (commandWord.equals("eat")) {
                goRoom(command);
            }else if (commandWord.equals("leave")) {
                goRoom(command);
            } else if (commandWord.equals("sprinkle")) {
                goRoom(command);
            }else if (commandWord.equals("quit")) {
                wantToQuit = quit(command);
            }
            // else command not recognised.
            return wantToQuit;
        }

        // implementations of user commands:

        /**
         * Print out some help information.
         * Here we print some stupid, cryptic message and a list of the
         * command words.
         */
        private void printHelp () {
            System.out.println("You are lost. You are alone. You wander");
            System.out.println("around at the " + room);
            System.out.println();
            System.out.println("Your command words are:");
            parser.showCommands();
        }

        /**
         * Try to in to one direction. If there is an exit, enter the new
         * room, otherwise print an error message.
         */
        private void goRoom (Command command)
        {
            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
                return;
            }

            String direction = command.getSecondWord();

            // Try to leave current room.
            Room nextRoom = currentRoom.getExit(direction);

            if (nextRoom == null) {
                System.out.println("There is no door!");
            } else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
            }
        }

        /**
         * "Quit" was entered. Check the rest of the command to see
         * whether we really quit the game.
         * @return true, if this command quits the game, false otherwise.
         */
        private boolean quit (Command command)
        {
            if (command.hasSecondWord()) {
                System.out.println("Quit what?");
                return false;
            } else {
                return true;  // signal that we want to quit
            }
        }
    }