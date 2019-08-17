package ar.com.nubank.model.enums;



public enum Direction {
    UP{
        @Override
        public Direction left() {
            return LEFT;
        }

        @Override
        public Direction right() {
            return RIGHT;
        }

        String value="↑";

        public String getValue() {
            return value;
        }
    },
    DOWN{
        String value="↓";

        @Override
        public Direction left() {
            return RIGHT;
        }

        @Override
        public Direction right() {
            return LEFT;
        }

        @Override
        public String getValue() {
            return value;
        }
    },
    LEFT{
        String value="←";
        @Override
        public String getValue() {
            return value;
        }

        @Override
        public Direction left() {
            return DOWN;
        }

        @Override
        public Direction right() {
            return UP;
        }
    },
    RIGHT{
        String value="→";
        @Override
        public String getValue() {
            return value;
        }

        @Override
        public Direction left() {
            return UP;
        }

        @Override
        public Direction right() {
            return DOWN;
        }
    };
    ;



    public abstract Direction left();
    public abstract Direction right();
    public abstract String getValue();

}
