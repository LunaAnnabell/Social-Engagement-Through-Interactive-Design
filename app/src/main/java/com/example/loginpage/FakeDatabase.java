package com.example.loginpage;

import java.util.Arrays;
import java.util.List;
/**
 * A fake database that holds lists of characters, events, and venues.
 * This class creates some sample data for testing and demonstration.
 */
public class FakeDatabase {

    /**
     * These Lists act as our database
     */
    public List<Characters> characterList;

    public List<Event> eventList;

    public List<Venue> venueList;

    /**
     * Constructor for our fake database. It populates the lists above.
     */
    public FakeDatabase() {

        /**
         * Dummy coordinates used to make the venues
         */
        Coordinates cord = new Coordinates(1.0, 1.0);

        /**
         * List of venues
         */
        venueList = Arrays.asList(
                new Venue("Buensbogcafe", "Address 1", cord, "Aalborg", "Cafe", "Buensbogcafe is a cafe and bookstore in Aalborg with LGBTQ+ literature and cool events.", R.drawable.buensbogcafe),
                new Venue("1000Fryd", "Address 2", cord, "Aalborg", "Bar", "1000Fryd is a bar where they play music", R.drawable.rigmor_and_support),
                new Venue("Aalborg Dinosaurs Discgolf", "Address 3", cord, "Aalborg", "Disc Golf", "Aalborg Dinosaurs Discgolf is a disc golf course in Aalborg", R.drawable.discgolf),
                new Venue("Kunsten", "Address 4", cord, "Aalborg", "Museum", "Kunsten is a museum in Aalborg", R.drawable.cruquis_at_kunsten),
                new Venue("Musikkens Hus", "Address 5", cord, "Aalborg", "Cultural Venue", "Musikkens Hus is a place for music in Aalborg", R.drawable.hjernero),

                // New venues added below
                new Venue("Studenterhuset", "Address 6", cord, "Aalborg", "Café & Games", "Stop by and try something new – our volunteers are ready to help you find the perfect board game and explain the rules.", R.drawable.board_game_night),
                new Venue("AKKC", "Address 7", cord, "Aalborg", "Theatre", "Aalborg Kongres & Kultur Center (AKKC) hosts concerts and musicals like 'We Will Rock You'.", R.drawable.rock_you),
                new Venue("Aalborg Portland Park", "Address 8", cord, "Aalborg", "Stadium", "Aalborg’s stadium where AaB plays home matches, including against Silkeborg I.F.", R.drawable.aab),
                new Venue("Art by Me", "Address 9", cord, "Aalborg", "Art Studio", "Art by Me hosts creative events like crystal glazing and wine tastings.", R.drawable.art_by_me),
                new Venue("Biffen Nordkraft", "Address 10", cord, "Aalborg", "Cinema", "Independent cinema in Nordkraft that also hosts fun events like movie quizzes.", R.drawable.biffen)
        );

        /**
         * List of events
         */
        eventList = Arrays.asList(
                new Event("Thursday 22-05", "Bookstore/cafe",
                        "Buensbogcafe is a cafe and bookstore in Aalborg with LGBTQ+ literature and cool events. It's a great place to meet new people and discover unique books.",
                        R.drawable.tipsy, venueList.get(0), "Tipsy"),

                new Event("Friday 09-05", "Rigmor and support",
                        "Join Rigmor as they take their 'Vælter i mørket' era to new heights. This concert will mix old favorites with fresh tracks from their critically acclaimed new album.",
                        R.drawable.bongo, venueList.get(1), "Bongo"),

                new Event("Saturday 10-05", "Night discgolfing",
                        "Aalborg discgolf group invites you to an exciting night of disc golfing with neon discs. It's a great way to experience the sport in a completely new light and a fun social event.",
                        R.drawable.rally, venueList.get(2), "Rally"),

                new Event("Thursday 22-05", "Croquis at Kunsten",
                        "Join us for croquis drawing in Kunsten’s exhibition space. Explore the beauty of the human body through sketching while surrounded by incredible art.",
                        R.drawable.sketchy, venueList.get(3), "Sketchy"),

                new Event("Thursday 22-05", "HjerneRO Talk by Mads",
                        "Sit back and enjoy Mads Hyldig’s talk on mental calm. Learn practical techniques for reducing stress and anxiety and how to introduce peace of mind into your daily life.",
                        R.drawable.glitch, venueList.get(4), "Glitch"),

                //  New events
                new Event("Tuesday 03-06", "Board Game Night #7",
                        "Stop by and try something new – our wonderful volunteers are ready to help you find the perfect game for your group and will even explain the rules to you.",
                        R.drawable.glitch, venueList.get(5), "Tipsy"),

                new Event("Friday 07-06", "We Will Rock You",
                        "The musical features all of Queen's immortal hits like Bohemian Rhapsody, Don’t Stop Me Now, We Are The Champions – and many more.",
                        R.drawable.bongo, venueList.get(6), "Bongo"),

                new Event("Sunday 09-06", "AaB - Silkeborg I.F.",
                        "Watch Aalborg’s home team AaB take on Silkeborg I.F. in an exciting football match at the city's main stadium.",
                        R.drawable.rally, venueList.get(7), "Rally"),

                new Event("Wednesday 12-06", "Art by Me: Crystal Glazing & Wine",
                        "Enjoy a creative night at Art by Me with crystal glazing and wine tasting – a perfect mix of art and relaxation.",
                        R.drawable.sketchy, venueList.get(8), "Sketchy"),

                new Event("Saturday 15-06", "Biffen's Film Quiz",
                        "Who said 'Louis, I think this is the beginning of a beautiful friendship'? Join Biffen's brand-new film quiz night to test your movie knowledge!",
                        R.drawable.glitch, venueList.get(9), "Glitch")
        );

        /**
         * List of characters
         */
        characterList = Arrays.asList(
                new Characters("Tipsy", "Social", "If your perfect day means having fun at a festival, trying new dishes at Street Food, and late drinks in Jomfru Ane Gade, Tipsy’s got your back. \n" +
                        "\n" +
                        "She’s all about fun vibes, new faces, and unforgettable nights. Ready to find your next plan?", R.drawable.tipsy, 0xFF308DF6), // Colour hex: #308DF6 (Blue)
                new Characters("Bongo", "Music", "If you’re the one chasing live gigs at Tusindfryd, digging through vinyls at Læsehesten, or catching everything from jazz to punk around town, Bongo’s on your wavelength. \n" +
                        "\n" +
                        "Aalborg’s music scene is calling — ready to find your next favorite sound?", R.drawable.bongo, 0xFFFF964A), // Colour hex: #FF964A (Orange)
                new Characters("Rally", "Active", "If your perfect day includes cheering at Aalborg Portland Park, climbing walls at Nordkraft, and diving into volleyball matches at Vestre Fjordpark, Rally’s got the same drive. \n" +
                        "\n" +
                        "Aalborg’s full of ways to move and meet — ready to get out there?", R.drawable.rally, 0xFFFC4780), // Colour hex: #FC4780 (Pink)
                new Characters("Sketchy", "Creative", "If you’re be into spray cans at Karolinelund, cozy sketching at Ulla Terkelsen or indie films at Biffen, Aalborg’s one big canvas if you know where to look - Sketchy’s already there.\n" +
                        "\n" +
                        "Ready to make your mark?", R.drawable.sketchy, 0xFF2ECC71), // Colour hex: #2ECC71 (Green)
                new Characters("Glitch", "Geeky", "If you wish to lose yourself in epic quests at Spilcaféen, dive into fantasy realms at the library, and recharge over  coffee at Buens Bogcafé. For anyone like Glitch who loves stories, strategy, and a little bit of magic, Aalborg is full of hidden gems. ", R.drawable.glitch, 0xFFFFDD57) // Colour hex: #FFDD57 (Yellow)
        );

        /**
         * Associate each event with its corresponding persona character based on matching names.
         */
        for (Characters character : characterList) {
            for (Event event : eventList) {
                if (event.getCharacterName().equals(character.getName())) {
                    character.addEvent(event);
                }
            }
        }
    }        // End of constructor

    /**
     * Returns the list of events
     * @return the list of events
     */
 public List<Event> getEventList() {
        return eventList;
    }

    /**
     * Returns the list of characters
     * @return the list of characters
     */
 public List<Characters> getCharacterList() {
        return characterList;
   }
}
