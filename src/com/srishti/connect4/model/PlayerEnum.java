package com.srishti.connect4.model;

public enum PlayerEnum {

    USER, COMPUTER;

    public boolean isUser()
    {
        return (this == PlayerEnum.USER);
    }

    public boolean isComputer()
    {
        return (this == PlayerEnum.COMPUTER);
    }

}
