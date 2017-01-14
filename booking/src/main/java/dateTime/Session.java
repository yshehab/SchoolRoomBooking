/*
 * Original comments by neo
 * if you wish to comment please add a handle
 * 
 * GPL 2.0
 * Intended for a single project
 */
package dateTime;

/**
 * @author neo
 */
public enum Session implements Repeat {

    SESSION_1("Session 1"),
    SESSION_2("Session 2"),
    SESSION_3("Session 3"),
    SESSION_4("Session 4"),
    SESSION_5("Session 5"),
    SESSION_6("Session 6"),
    SESSION_7("Session 7"),
    SESSION_8("Session 8"),
    SESSION_9("Session 9"),
    SESSION_10("Session 10"),
    SESSION_11("Session 11"),
    SESSION_12("Session 12"),
    SESSION_13("Session 13"),
    NONE("None");
    private String display;

    private Session(String display) {
        this.display = display;
    }

    @Override
    public String getDisplayString() {
        return display;
    }

    /**
     *
     * @param rate A repeatRate enum. The only enum that Session supports are NEXT and SINGLE.
     * @return The Session after this Session, or NONE if the RepeatRate rate is not 1 or the Session
     * is the last Session.
     */
    @Override
    public Session add(RepeatRate rate) {
        final int repeat = rate.getRate();
        final boolean LAST = this.ordinal() == (Session.NONE.ordinal() - 1);
        final Session result;
        if (repeat != 1 || LAST) {
            result = Session.NONE;
        } else {
            int next = this.ordinal() + repeat;
            result = Session.values()[next];
        }
        return result;
    }

    /**
     *
     * @param obj A Session
     * @return Returns true if the obj argument is naturally ordered after the receiver.
     */
    @Override
    public boolean isAfter(Object obj) {
        Session session = (Session) obj;
        return session.ordinal() > this.ordinal();
    }
}
