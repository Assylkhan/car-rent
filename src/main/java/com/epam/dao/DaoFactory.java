package com.epam.dao;

import com.epam.dao.H2.H2DaoFactory;

public abstract class DaoFactory {


    public static DaoFactory getDaoFactory(DatabaseType dbType){
        switch (dbType){
            case  H2: return new H2DaoFactory();
            default: return null;
        }
    }

    public abstract void releaseConnections();

    public abstract DaoManager getDaoManager();
}