package com.epam.dao;

public abstract class DaoFactory {


    public static DaoFactory getDaoFactory(Database whichDb){
        switch (whichDb){
            case  H2: return new H2DaoFactory();
            default: return null;
        }
    }

    public abstract void releaseConnections();

    public abstract DaoManager getDaoManager();
}