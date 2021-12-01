package com.marcos.breakfast.domain.service;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import org.springframework.stereotype.Component;

@Component
public class Utils {
		
	public String spaceRemoving(String text) {
		return text.trim().replaceAll("\\s+", " ");
	}
	

	public static String cpfMask(String cpf) throws ParseException {
        
        try{
            MaskFormatter formater =  new MaskFormatter("###.###.###-##");
    		JFormattedTextField txtCpf = new JFormattedTextField(formater);
    		txtCpf.setText(cpf);
    		cpf = txtCpf.getText();
    		return cpf;
        }catch(Exception e){
            e.printStackTrace();
		    return "";
        }
	}
}
