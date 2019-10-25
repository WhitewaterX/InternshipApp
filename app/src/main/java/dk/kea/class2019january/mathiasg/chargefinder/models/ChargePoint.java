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

    @Override
    public String toString()
    {
        return "ChargePoint{" +
                "ID=" + ID +
                ", operatorInfo=" + operatorInfo +
                ", addressInfo=" + addressInfo +
                ", connections=" + connections +
                '}';
    }

    public int getID()
    {
        return ID;
    }

    public OperatorInfo getOperatorInfo()
    {
        return operatorInfo;
    }

    public AddressInfo getAddressInfo()
    {
        return addressInfo;
    }

    public ArrayList<Connections> getConnections()
    {
        return connections;
    }
}
