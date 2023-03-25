package com.example.tp_6.manager;


import com.example.tp_6.model.Antenne;

public interface AntenneDataManagerCallBack {

    void getTimeResponseSuccess(Antenne antenne);
    void getTimeResponseError(String message);

}
