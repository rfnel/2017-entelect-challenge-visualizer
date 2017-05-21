package za.co.entelect.domain.state;

import java.awt.*;

public class Cell {

    public boolean Occupied;

    public boolean Hit;

    public int X;

    public int Y;

    public Point getPoint() {

        return new Point(X, Y);
    }
}
