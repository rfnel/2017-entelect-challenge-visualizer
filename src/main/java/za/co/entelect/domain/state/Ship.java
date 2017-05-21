package za.co.entelect.domain.state;

import java.util.ArrayList;

public class Ship {

    public boolean Destroyed;

    public boolean Placed;

    public za.co.entelect.domain.command.ship.ShipType ShipType;

    public ArrayList<Weapon> Weapons;

    public ArrayList<Cell> Cells;
}
