package com.kosmo.common.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CommonUtil {

    public String addMsgLoc(Model model, String msg, String loc){
        model.addAttribute("msg", msg);
        model.addAttribute("loc", loc);

        return "message";
    }//----------------------------


    public String addMsgBack(Model model, String msg){
        return this.addMsgLoc(model, msg, "javascript:history.back()");
    }

}
