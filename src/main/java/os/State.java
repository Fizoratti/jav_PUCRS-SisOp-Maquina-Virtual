public enum State {
    NEW("New"), 
    READY("Ready"), 
    RUNNING("Running"), 
    BLOCKED("Blocked"), 
    SUSPENDED("Suspended"), 
    EXIT("Exit");

    private String type;

    State(String _type) {
        setType(_type);
    }


    void setType(String _type) {
        this.type = _type;
    }

    String get() {
        return this.type;
    }
}