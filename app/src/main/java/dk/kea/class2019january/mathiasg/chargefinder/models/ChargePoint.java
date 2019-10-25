package dk.kea.class2019january.mathiasg.chargefinder.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class ChargePoint
{
    @Expose
    private int ID;

    @Expose
    private OperatorInfo operatorInfo;

    @Expose
    private AddressInfo addressInfo;

    @Expose
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
