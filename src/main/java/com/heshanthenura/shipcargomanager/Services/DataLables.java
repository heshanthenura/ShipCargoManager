package com.heshanthenura.shipcargomanager.Services;

import javafx.scene.text.Text;

public class DataLables {
    public static Text dataIdLbl;
    public static Text dataSlotLbl;
    public static Text dataAdLbl;
    public static Text dataRDDLbl;
    public static Text dataRDLbl;

    public void initLables(Text dataIdLbl, Text dataSlotLbl, Text dataAdLbl, Text dataRDDLbl, Text dataRDLbl) {
        DataLables.dataIdLbl =dataIdLbl;
        DataLables.dataSlotLbl =dataSlotLbl;
        DataLables.dataAdLbl =dataAdLbl;
        DataLables.dataRDDLbl =dataRDDLbl;
        DataLables.dataRDLbl =dataRDLbl;
    }

    public void setLables(int id,int slotId,String ad,String rdd,String rd){
        dataIdLbl.setText(String.valueOf(id));
        dataSlotLbl.setText(String.valueOf(slotId));
        dataAdLbl.setText(ad);
        dataRDDLbl.setText(rdd);
        dataRDLbl.setText(rd);
    }


}
