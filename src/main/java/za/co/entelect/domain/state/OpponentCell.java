package za.co.entelect.domain.state;

import java.awt.*;

public class OpponentCell {

    public boolean Damaged;

    public boolean Missed;

    public int X;

    public int Y;

    public Point getPoint() {

        return new Point(X, Y);
    }
}
