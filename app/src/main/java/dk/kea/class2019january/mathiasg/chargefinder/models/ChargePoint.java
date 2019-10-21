package dk.kea.class2019january.mathiasg.chargefinder.models;

import java.util.ArrayList;

public class ChargePoint
{
    private int ID;
    private OperatorInfo operatorInfo;
    private AddressInfo addressInfo;
    private ArrayList<Connections> connections;

    public ChargePoint(int ID, OperatorInfo operatorInfo, AddressInfo addressInfo, ArrayList<Connections> connections)
    {
        this.ID = ID;
        this.operatorInfo = operatorInfo;
        this.addressInfo = addressInfo;
        this.connections = connections;
    }
}
