package com.franquicia.backend.cadenaResponsabilidad;

import org.json.JSONArray;
import org.json.JSONObject;

public class Menu implements Executor{

    private Executor next;

    public Menu(Executor exe){
        this.next = exe;
    }

    @Override
    public void method(JSONObject cuerpo1){
        System.out.println("accion " + cuerpo1.get("accion"));
        if(cuerpo1.get("accion").equals("menu")){
            System.out.println("Listado "+ cuerpo1.get("listado")); //Printeo el listado
            JSONArray jarr = new JSONArray(cuerpo1.get("listado").toString());
            //Obtengo el listado. Para poder obtenerlo debo convertirlo a String. Esto es debido a
            //que es un Array Json
            for (int i = 0; i < jarr.length(); i++) {
                System.out.println(jarr.getJSONObject(i).getInt("precio"));
                //Recorro el listado y obtengo los precios. A modo de ejemplo de como recorrer el array
            }
        }else{
            next.method(cuerpo1);
        }
    }

}
