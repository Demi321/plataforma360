/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import com.opentok.exception.OpenTokException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Vostro Placas
 */
public class ParseJson {

    public static void main(String[] args) throws OpenTokException, InterruptedException, ParseException {
        //String credenciales = null;
        String json="[\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ABORTO\",\n" +
"		\"id\": 10302,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ABUSO DE CONFIANZA\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ABUSO SEXUAL\",\n" +
"		\"id\": 30701,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE ACUATICO CON HERIDOS\",\n" +
"		\"id\": 10101,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE ACUATICO SIN HERIDOS\",\n" +
"		\"id\": 10101,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE AEREO CON HERIDOS\",\n" +
"		\"id\": 10102,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE AEREO SIN HERIDOS\",\n" +
"		\"id\": 10102,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE CHOQUE DE VEHICULO CON HERIDOS\",\n" +
"		\"id\": 10104,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE CHOQUE DE VEHICULO MOTOCICLETA CON HERIDOS\",\n" +
"		\"id\": 10103,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE CHOQUE DE VEHICULO MOTOCICLETA SIN HERIDOS\",\n" +
"		\"id\": 30309,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE CHOQUE DE VEHICULO SIN HERIDOS\",\n" +
"		\"id\": 30309,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ACCIDENTE DERRAPAMIENTO CON HERIDOS\",\n" +
"		\"id\": 10103,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ACCIDENTE DERRAPAMIENTO SIN HERIDOS\",\n" +
"		\"id\": 30309,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE FERROVIARIO CON HERIDOS\",\n" +
"		\"id\": 10105,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE FERROVIARIO SIN HERIDOS\",\n" +
"		\"id\": 10105,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ACCIDENTE SALIDA DE CAMINO CON HERIDOS\",\n" +
"		\"id\": 10104,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ACCIDENTE SALIDA DE CAMINO SIN HERIDOS\",\n" +
"		\"id\": 30309,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACCIDENTE VOLCADURA CON HERIDOS\",\n" +
"		\"id\": 10104,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ACCIDENTE VOLCADURA SIN HERIDOS\",\n" +
"		\"id\": 30309,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACTIVACION DE ALARMA A CASA HABITACION\",\n" +
"		\"id\": 30401,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACTIVACION DE ALARMA A COMERCIO\",\n" +
"		\"id\": 30401,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACTIVACION DE ALARMA BANCARIA\",\n" +
"		\"id\": 30401,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ACTIVACION DE ALARMA EN EDIFICACION\",\n" +
"		\"id\": 30401,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"AGRESION FISICA\",\n" +
"		\"id\": 30909,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"AGRESION VERBAL\",\n" +
"		\"id\": 31002,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ALLANAMIENTO DE MORADA\",\n" +
"		\"id\": 31001,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ALMACENAMIENTO DE COHETES FUEGOS ARTIFICIALES\",\n" +
"		\"id\": 30808,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ALMACENAMIENTO DE DROGA\",\n" +
"		\"id\": 31007,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"AMENAZA CON ARMA\",\n" +
"		\"id\": 31002,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"AMENAZA CON OBJETO PUNZO CORTANTE\",\n" +
"		\"id\": 31002,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"AMENAZA DE ARTEFACTO EXPLOSIVO\",\n" +
"		\"id\": 31002,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"AMENAZA ESCRITA\",\n" +
"		\"id\": 31002,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"AMENAZA POR OBJETO CONTUNDENTE\",\n" +
"		\"id\": 31002,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"AMENAZA VERBAL\",\n" +
"		\"id\": 31002,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"APOYO ANIMAL MUERTO\",\n" +
"		\"id\": 60102,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"APOYO ANIMAL VARADO\",\n" +
"		\"id\": 20403,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"APOYO ATERRIZAJE FORZOSO DE VEHICULO AEREO\",\n" +
"		\"id\": 10102,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"APOYO CON REMOLQUE DE VEHICULO\",\n" +
"		\"id\": 60103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"APOYO CON SERVICIO MECANICO\",\n" +
"		\"id\": 60103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"APOYO CON TRASLADO DE PERSONAS AEREO\",\n" +
"		\"id\": 60103,\n" +
"		\"Dependencias\": \"CruzRoja,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"APOYO CON TRASLADO DE PERSONAS TERRESTRE\",\n" +
"		\"id\": 60103,\n" +
"		\"Dependencias\": \"CruzRoja,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APOYO EVACUACION DE PERSONAS\",\n" +
"		\"id\": 60103,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"APOYO GANADO VAGO\",\n" +
"		\"id\": 60103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APOYO MALTRATO DE ANIMAL\",\n" +
"		\"id\": 50303,\n" +
"		\"Dependencias\": \"DPSM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"APOYO PARTO EN PROCESO\",\n" +
"		\"id\": 10301,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APOYO PERSECUCIÓN POLICIAL\",\n" +
"		\"id\": 30306,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"APOYO POR AMENAZA DE ABORTO\",\n" +
"		\"id\": 10302,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"APOYO POR AMENAZA DE SUICIDIO\",\n" +
"		\"id\": 30905,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APOYO REVISIONES MEDICAS\",\n" +
"		\"id\": 10316,\n" +
"		\"Dependencias\": \"CruzRoja\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APUESTA ILEGAL EN ARRANCONES DE VEHICULOS\",\n" +
"		\"id\": 30302,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APUESTA ILEGAL EN EVENTOS DEPORTIVOS\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APUESTA ILEGAL EN JUEGOS DE AZAR\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"APUESTA ILEGAL EN PELEAS DE ANIMALES\",\n" +
"		\"id\": 31111,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ATENTADO AL PUDOR\",\n" +
"		\"id\": 30703,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ATROPELLAMIENTO\",\n" +
"		\"id\": 10116,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"AUTOROBO\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"CONGESTIONAMIENTO VIAL\",\n" +
"		\"id\": 50301,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"CONTROL DE ANIMAL PELIGROSO PANAL ENJAMBRE\",\n" +
"		\"id\": 20203,\n" +
"		\"Dependencias\": \"ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"CONTROL DE ANIMAL PELIGROSO PERRO\",\n" +
"		\"id\": 20103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"CONTROL DE ANIMAL PELIGROSO VIBORA\",\n" +
"		\"id\": 20103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"CORRUPCION DE MENOR\",\n" +
"		\"id\": 30711,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"DAÑO A PROPIEDAD PRIVADA\",\n" +
"		\"id\": 30404,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"DAÑO A PROPIEDAD PUBLICA\",\n" +
"		\"id\": 30404,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"DERRUMBE DE CERRO\",\n" +
"		\"id\": 20202,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"DERRUMBE DE EDIFICACION\",\n" +
"		\"id\": 20202,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"DERRUMBE DE EXCAVACION\",\n" +
"		\"id\": 20202,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"DERRUMBE MINA\",\n" +
"		\"id\": 20202,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"DESALOJO DE PERSONAS\",\n" +
"		\"id\": 50301,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"DESBORDAMIENTO DE ARROYO\",\n" +
"		\"id\": 20106,\n" +
"		\"Dependencias\": \"ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"DESBORDAMIENTO DE CANAL\",\n" +
"		\"id\": 20106,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"DESBORDAMIENTO DE DIQUE PRESA\",\n" +
"		\"id\": 20106,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"DESBORDAMIENTO DE RIO\",\n" +
"		\"id\": 20106,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"DESFILE\",\n" +
"		\"id\": 50101,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"DESTRUCCION DE DROGA\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"DETENCIONES POR ORDEN DE APREHENSION\",\n" +
"		\"id\": 50302,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"DISPARO DE ARMA\",\n" +
"		\"id\": 30202,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"Demo\",\n" +
"		\"id\": 80101,\n" +
"		\"Dependencias\": \"\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ELECCIONES ACARREO DE PERSONAS\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ELECCIONES ALTERACION DEL ORDEN EN CASILLA\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ELECCIONES ATENTADO CONTRA CASILLAS ELECTORALES\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"ELECCIONES CIERRE DE CASILLA\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ELECCIONES COMPRA DE VOTOS\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ELECCIONES MANIFESTACION CON BLOQUEO DE ACCESO A CASILLA\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"ELECCIONES OTRO\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ELECCIONES VEHICULO CON PROPAGANDA ELECTORAL\",\n" +
"		\"id\": 31005,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ENFRENTAMIENTO ARMADO ENTRE MILITARES Y CIVILES\",\n" +
"		\"id\": 30803,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ENFRENTAMIENTO ARMADO ENTRE PERSONAS CIVILES\",\n" +
"		\"id\": 30803,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ENFRENTAMIENTO ARMADO ENTRE POLICIAS Y CIVILES\",\n" +
"		\"id\": 30803,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXPLOSION DE ALCANTARILLA\",\n" +
"		\"id\": 20107,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXPLOSION DE ARTEFACTO EXPLOSIVO\",\n" +
"		\"id\": 20107,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXPLOSION DE BOMBA DE ABASTECIMIENTO\",\n" +
"		\"id\": 20107,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXPLOSION DE LINEA DE CONDUCCION DE SUSTANCIA GASEOSA\",\n" +
"		\"id\": 20107,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXPLOSION DE LINEA DE CONDUCCION DE SUSTANCIA LIQUIDA\",\n" +
"		\"id\": 20107,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXPLOSION DE TANQUE DE ALMACENAMIENTO\",\n" +
"		\"id\": 20107,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXPLOSION DE VEHICULO\",\n" +
"		\"id\": 20107,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"EXTORSION\",\n" +
"		\"id\": 30406,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"EXTRAVIO DE ARMA DE FUEGO\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"EXTRAVIO DE PLACAS DE CIRCULACION DE VEHICULO\",\n" +
"		\"id\": 50103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"EXTRAVIO DE VEHICULO ACUATICO\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"EXTRAVIO DE VEHICULO AEREO\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"EXTRAVIO DE VEHICULO TERRESTRE\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALSIFICACION DE DOCUMENTOS\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS A LA MORAL\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS ALTERACION DEL ORDEN EN PROPIEDAD PRIVADA\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS ALTERACION DEL ORDEN EN VIA PUBLICA\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS ARROJAR BASURA EN LA VIA PUBLICA\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS ATISBAR\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS CONDUCIR EN ESTADO DE EBRIEDAD\",\n" +
"		\"id\": 31103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS CONTAMINACION DE SUELO, AIRE Y AGUA\",\n" +
"		\"id\": 20201,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS DETONACION DE COHETES\",\n" +
"		\"id\": 30205,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS DORMIDO EN VIA PUBLICA\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS DROGARSE EN LUGARES PUBLICOS PRIVADA\",\n" +
"		\"id\": 31105,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS ENCENDER FOGATAS Y COHETES\",\n" +
"		\"id\": 30205,\n" +
"		\"Dependencias\": \"DSPM\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS ESTACIONARSE EN LUGAR PROHIBIDO\",\n" +
"		\"id\": 30310,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS GRAFITI\",\n" +
"		\"id\": 31108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS INGERIR BEBIDAS PROHIBIDAS EN VIA PUBLICA\",\n" +
"		\"id\": 31104,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS MAL USO DEL AGUA\",\n" +
"		\"id\": 31114,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"FALTAS PROSTITUCION\",\n" +
"		\"id\": 30708,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"FALTAS RIÑA\",\n" +
"		\"id\": 31112,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FALTAS VEHICULO A EXCESO DE VELOCIDAD\",\n" +
"		\"id\": 30305,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"FRAUDE\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"FUGA DE REOS DE CARCEL MUNICIPAL\",\n" +
"		\"id\": 31006,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"FUGA DE REOS DEL CECJUDE\",\n" +
"		\"id\": 31006,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"FUGA DE REOS DEL CECJUDE EN MOVIL DE TRASLADO\",\n" +
"		\"id\": 31006,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"FUGA DE REOS DEL TUTELAR\",\n" +
"		\"id\": 31006,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"FUGA DE SUSTANCIA TOXICA GASEOSA\",\n" +
"		\"id\": 20108,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"FUGA DE SUSTANCIA TOXICA LIQUIDA\",\n" +
"		\"id\": 20108,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HELADA\",\n" +
"		\"id\": 20205,\n" +
"		\"Dependencias\": \"ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HOMICIDIO CON ARMA\",\n" +
"		\"id\": 30906,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HOMICIDIO CON FUEGO\",\n" +
"		\"id\": 30906,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HOMICIDIO CON OBJETO CONTUNDENTE\",\n" +
"		\"id\": 30906,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HOMICIDIO CON OBJETO PUNZO CORTANTE\",\n" +
"		\"id\": 30906,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HOMICIDIO POR ASFIXIA\",\n" +
"		\"id\": 30906,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HOMICIDIO POR INMERSION EN MEDIO LIQUIDO\",\n" +
"		\"id\": 30906,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"HOMICIDIO SE IGNORA CAUSA\",\n" +
"		\"id\": 30906,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO A CASA HABITACION\",\n" +
"		\"id\": 20301,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO A COMERCIO\",\n" +
"		\"id\": 20304,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"INCENDIO DE BALDIO PREDIO\",\n" +
"		\"id\": 20308,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO DE BOMBA DE ABASTECIMIENTO\",\n" +
"		\"id\": 20304,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO DE EDIFICACION\",\n" +
"		\"id\": 20305,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"INCENDIO DE FORRAJES\",\n" +
"		\"id\": 20309,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO DE LINEA DE CONDUCCION DE SUSTANCIA GASEOSA\",\n" +
"		\"id\": 20310,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO DE LINEA DE CONDUCCION DE SUSTANCIA LIQUIDA\",\n" +
"		\"id\": 20310,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"INCENDIO DE SEMBRADIO PARCELA\",\n" +
"		\"id\": 20309,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO DE TANQUE DE ALMACENAMIENTO\",\n" +
"		\"id\": 20310,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO DE VEHICULO\",\n" +
"		\"id\": 20303,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INCENDIO DE VEHICULO VAGON DE FERROCARRIL\",\n" +
"		\"id\": 20303,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"INCENDIO FORESTAL\",\n" +
"		\"id\": 20307,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"INUNDACION EN AREA RURAL\",\n" +
"		\"id\": 20207,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"INUNDACION EN AREA URBANA\",\n" +
"		\"id\": 20207,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"INVASION DE PROPIEDAD AJENA\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LESIONES CON ARMA\",\n" +
"		\"id\": 30901,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LESIONES CON FUEGO\",\n" +
"		\"id\": 30901,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LESIONES CON OBJETO CONTUNDENTE\",\n" +
"		\"id\": 30901,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LESIONES CON OBJETO PUNZO CORTANTE\",\n" +
"		\"id\": 30901,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LLAMADA DE PRUEBA\",\n" +
"		\"id\": 70102,\n" +
"		\"Dependencias\": \"AgenciaFederal,CruzRoja,DSPM,Marina,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,Bomberos,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LOCALIZACION DE ARMA\",\n" +
"		\"id\": 50202,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LOCALIZACION DE ARTEFACTO EXPLOSIVO\",\n" +
"		\"id\": 30102,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LOCALIZACION DE CASCAJOS DE ARMA DE FUEGO\",\n" +
"		\"id\": 30102,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE COMBUSTIBLE\",\n" +
"		\"id\": 20102,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE DROGA LABORATORIO CLANDESTINO\",\n" +
"		\"id\": 20102,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE DROGA PLANTIO\",\n" +
"		\"id\": 20102,\n" +
"		\"Dependencias\": \"SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE DROGA PORCIONES PAQUETES\",\n" +
"		\"id\": 20102,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LOCALIZACION DE FETO HUMANO\",\n" +
"		\"id\": 50201,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE FOSA CLANDESTINA\",\n" +
"		\"id\": 50201,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE MANTA\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE MEDICAMENTO\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE MERCANCIA ROBADA\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"LOCALIZACION DE MUNICIONES DE ARMA DE FUEGO\",\n" +
"		\"id\": 30102,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE OSAMENTA HUMANA\",\n" +
"		\"id\": 50201,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE PARTES DE CUERPO HUMANO\",\n" +
"		\"id\": 50201,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE PERSONA PERDIDA\",\n" +
"		\"id\": 50302,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"LOCALIZACION DE UNIFORMES MILITARES\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"LOCALIZACION DE VEHICULO ACUATICO\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"LOCALIZACION DE VEHICULO AEREO\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"Bomberos,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"LOCALIZACION DE VEHICULO TERRESTRE\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"MANIFESTACION O MITIN CON BLOQUEO DE VIAS DE COMUNICACIÓN\",\n" +
"		\"id\": 31110,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"MANIFESTACION O MITIN CON TOMA DE INSTALACIONES\",\n" +
"		\"id\": 31110,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"MANIFESTACION O MITIN SIN BLOQUEO DE VIAS DE COMUNICACION\",\n" +
"		\"id\": 31110,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"MOTIN DE REOS\",\n" +
"		\"id\": 30807,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"NOTIFICACIÓN DE TRASLADO DE VEHICULO CON SERVIDORES PÚBLICOS\",\n" +
"		\"id\": 70108,\n" +
"		\"Dependencias\": \"PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA ABANDONADA\",\n" +
"		\"id\": 30501,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA ASFIXIANDOSE\",\n" +
"		\"id\": 10203,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA DESAPARECIDA\",\n" +
"		\"id\": 30602,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA DESMANTELANDO VEHICULO\",\n" +
"		\"id\": 30411,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"PERSONA EN CRISIS NERVIOSA\",\n" +
"		\"id\": 10318,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"PERSONA EN SITUACION DE CALLE\",\n" +
"		\"id\": 50304,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA ENFERMA\",\n" +
"		\"id\": 10310,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA ENFERMA MENTALMENTE\",\n" +
"		\"id\": 10318,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA INCONSCIENTE\",\n" +
"		\"id\": 10308,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA INDOCUMENTADA\",\n" +
"		\"id\": 30710,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA INTOXICADA POR ALCOHOL\",\n" +
"		\"id\": 10306,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA INTOXICADA POR ALIMENTOS\",\n" +
"		\"id\": 10310,\n" +
"		\"Dependencias\": \"CruzRoja,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA INTOXICADA POR CONSUMIR DROGAS ENERVANTES\",\n" +
"		\"id\": 10315,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA INTOXICADA POR CONSUMO DE MEDICAMENTOS\",\n" +
"		\"id\": 10315,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA INTOXICADA POR INGERIR SUSTANCIAS TOXICAS\",\n" +
"		\"id\": 10315,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA INTOXICADA POR INHALAR SUSTANCIAS TOXICAS\",\n" +
"		\"id\": 10315,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL CON ARMA DE FUEGO\",\n" +
"		\"id\": 10209,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR ATRAPAMIENTO DE PARTE CORPORAL\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR CAIDA\",\n" +
"		\"id\": 10204,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR DESCARGA ELECTRICA\",\n" +
"		\"id\": 10205,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR GOLPES\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR HERIDA CORTANTE CON EQUIPO MAQUINARIA\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR HERIDA CORTANTE CON OBJETO PUNZO CORTANTE\",\n" +
"		\"id\": 10208,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR QUEMADURA\",\n" +
"		\"id\": 10211,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA ACCIDENTAL POR QUEMADURA CON COHETES FUEGOS ARTIFICIALES\",\n" +
"		\"id\": 10211,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ANIMAL MORDEDURA\",\n" +
"		\"id\": 10210,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ANIMAL PATADA DE ANIMAL\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ANIMAL PICADURA DE ABEJAS\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ANIMAL PICADURA DE ALACRAN\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ANIMAL PICADURA DE MANTARRAYA\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ANIMAL PICADURA ERIZO\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ANIMAL QUEMADOR\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ARMA DE FUEGO\",\n" +
"		\"id\": 10209,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR ARMA PUNZO CORTANTE\",\n" +
"		\"id\": 10208,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR CAIDA DE VEHICULO\",\n" +
"		\"id\": 10204,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR GOLPES\",\n" +
"		\"id\": 10220,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA LESIONADA POR INTENTO DE SUICIDIO\",\n" +
"		\"id\": 30908,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA LIBERADA\",\n" +
"		\"id\": 30609,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL CON ARMA DE FUEGO\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR ASFIXIA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR ATRAPAMIENTO DE PARTE CORPORAL\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR CAIDA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR DESCARGA ELECTRICA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR GOLPE CON MAQUINARIA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR GOLPE CON OBJETO CONTUNDENTE\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR GOLPE POR CAERLE OBJETO ENCIMA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR HERIDA CORTANTE CON EQUIPO MAQUINARIA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR HERIDA CORTANTE CON OBJETO PUNZO CORTANTE\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR QUEMADURA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA ACCIDENTAL POR QUEMADURA CON COHETES FUEGOS ARTIFICIALES\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA NATURALMENTE\",\n" +
"		\"id\": 10317,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ANIMAL MORDEDURA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ANIMAL PATADA DE ANIMAL\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ANIMAL PICADURA DE ABEJA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ANIMAL PICADURA DE ALACRAN\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ANIMAL PICADURA DE MANTARRAYA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ARMA DE FUEGO\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ARMA PUNZO CORTANTE\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ASFIXIA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ATAQUE CARDIACO\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ATAQUE EPILEPTICO\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR DESHIDRATACION\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR ENFERMEDAD\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR GOLPES\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR INMERSION EN MEDIO LIQUIDO\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR INTOXICACION ALCOHOLICA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR INTOXICACION ALIMENTICIA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR INTOXICACION POR CONSUMO DE DROGAS ENERVANTES\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR INTOXICACION POR CONSUMO DE MEDICAMENTOS\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR INTOXICACION POR INGERIR SUSTANCIAS TOXICAS\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR INTOXICACION POR INHALAR SUSTANCIAS TOXICAS\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA POR SUICIDIO\",\n" +
"		\"id\": 30908,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA MUERTA SE IGNORA CAUSA\",\n" +
"		\"id\": 10219,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA PERDIDA\",\n" +
"		\"id\": 30602,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA SOSPECHOSA\",\n" +
"		\"id\": 30904,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"PERSONA TIRADA EN LA VIA PUBLICA\",\n" +
"		\"id\": 10308,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PORTACION DE ARMA\",\n" +
"		\"id\": 30204,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PORTACION DE ARTEFACTO EXPLOSIVO\",\n" +
"		\"id\": 30204,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PORTACION DE CARTUCHOS DE ARMA DE FUEGO\",\n" +
"		\"id\": 30204,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PORTACION DE PLACA DE CIRCULACION CON REPORTE DE ROBO\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PORTACION DE PLACA DE CIRCULACION USO INDEBIDO\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"POSESION DE ARMA\",\n" +
"		\"id\": 30204,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"POSESION DE DROGA\",\n" +
"		\"id\": 31007,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"PRIVACION ILEGAL DE LA LIBERTAD\",\n" +
"		\"id\": 30603,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"RAPTO\",\n" +
"		\"id\": 30609,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A BANCO\",\n" +
"		\"id\": 30434,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A CAJERO AUTOMATICO\",\n" +
"		\"id\": 30410,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A CASA HABITACION\",\n" +
"		\"id\": 30414,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A COMERCIO\",\n" +
"		\"id\": 30420,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A ESCUELA\",\n" +
"		\"id\": 30416,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A GASOLINERA\",\n" +
"		\"id\": 30418,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A INSTITUCION PRIVADA\",\n" +
"		\"id\": 30420,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A INSTITUCION PUBLICA\",\n" +
"		\"id\": 30420,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A PERSONA\",\n" +
"		\"id\": 30422,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A VEHICULO ACCESORIOS\",\n" +
"		\"id\": 30411,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A VEHICULO DE TRANSPORTE DE CARGA FERROCARRIL\",\n" +
"		\"id\": 30439,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO A VEHICULO DE TRANSPORTE DE PASAJEROS\",\n" +
"		\"id\": 30424,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO A VEHICULO PLACAS\",\n" +
"		\"id\": 30440,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO AL INTERIOR DEL VEHICULO\",\n" +
"		\"id\": 30403,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO DE ANIMAL OTRO\",\n" +
"		\"id\": 30412,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO DE CADAVER\",\n" +
"		\"id\": 30806,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO DE CHEQUE\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO DE GANADO (ABIGEATO)\",\n" +
"		\"id\": 30412,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO DE VEHICULO\",\n" +
"		\"id\": 30430,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO DE VEHICULO MOTOCICLETA\",\n" +
"		\"id\": 30430,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO EN GRADO DE TENTATIVA\",\n" +
"		\"id\": 30446,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A BANCO\",\n" +
"		\"id\": 30433,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A CASA HABITACION\",\n" +
"		\"id\": 30413,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A COMERCIO\",\n" +
"		\"id\": 30419,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A ESCUELA\",\n" +
"		\"id\": 30415,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A GASOLINERA\",\n" +
"		\"id\": 30417,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A INSTITUCION PRIVADA\",\n" +
"		\"id\": 30419,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A INSTITUCION PUBLICA\",\n" +
"		\"id\": 30419,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO A PERSONA\",\n" +
"		\"id\": 30421,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO DE VEHICULO\",\n" +
"		\"id\": 30429,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"ROBO VIOLENTO DE VEHICULO MOTOCICLETA\",\n" +
"		\"id\": 30429,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"SALVAMENTO ACUATICO DE PERSONA EN PELIGRO DE MUERTE\",\n" +
"		\"id\": 20401,\n" +
"		\"Dependencias\": \"CruzRoja,DSPM,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"SECUESTRO\",\n" +
"		\"id\": 30609,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO ALCANTARILLA SIN TAPA\",\n" +
"		\"id\": 40105,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO ALCANTARILLA TAPADA\",\n" +
"		\"id\": 60101,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO ATENCION VIAL\",\n" +
"		\"id\": 40110,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO CABLE CAIDO\",\n" +
"		\"id\": 40106,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO CABLE ELECTRICO CAIDO\",\n" +
"		\"id\": 40106,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO CORTO CIRCUITP\",\n" +
"		\"id\": 40107,\n" +
"		\"Dependencias\": \"ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO FUGA DE AGUA\",\n" +
"		\"id\": 60103,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO MALOS OLORES\",\n" +
"		\"id\": 20111,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO OBJETO FIJO CAIDO\",\n" +
"		\"id\": 20113,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO SEMAFORO DESCOMPUESTO\",\n" +
"		\"id\": 40104,\n" +
"		\"Dependencias\": \"DSPM\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SERVICIO PUBLICO SOLICITUD DE RONDINES\",\n" +
"		\"id\": 50305,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"SIMULACRO\",\n" +
"		\"id\": 50301,\n" +
"		\"Dependencias\": \"Bomberos,CruzRoja,DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"SISMO\",\n" +
"		\"id\": 20209,\n" +
"		\"Dependencias\": \"ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"SUSTRACCION DE MENOR\",\n" +
"		\"id\": 30607,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TALA CLANDESTINA DE ARBOLES\",\n" +
"		\"id\": 31010,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TOMA CLANDESTINA DE COMBUSTIBLE\",\n" +
"		\"id\": 30409,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE ANIMALES\",\n" +
"		\"id\": 31012,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE ARMAS\",\n" +
"		\"id\": 30207,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE BEBIDAS\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE COHETES FUEGOS ARTIFICIALES\",\n" +
"		\"id\": 30808,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE DROGAS\",\n" +
"		\"id\": 31014,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE ELECTRODOMESTICOS\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE INDOCUMENTADOS\",\n" +
"		\"id\": 30710,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaFederal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"TRAFICO ILEGAL DE PRODUCTOS ALIMENTICIOS\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"TRASLADO FORZOSO DE DROGADICTO\",\n" +
"		\"id\": 50301,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"USO DE BILLETE FALSO\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"USO DE CHEQUE FALSO\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"USURPACION DE FUNCIONES\",\n" +
"		\"id\": 50106,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"VEHICULO ABANDONADO\",\n" +
"		\"id\": 30101,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VEHICULO OBSTRUYENDO VIA DE COMUNICACION\",\n" +
"		\"id\": 30310,\n" +
"		\"Dependencias\": \"DSPM,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VEHICULO RECUPERADO CON REPORTE DE ROBO\",\n" +
"		\"id\": 50203,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"NORMAL\",\n" +
"		\"Incidente\": \"VEHICULO RECUPERADO CON REPORTE DE ROBO MOTOCICLETA\",\n" +
"		\"id\": 50203,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VEHICULO SOSPECHOSO\",\n" +
"		\"id\": 30307,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE ANIMALES\",\n" +
"		\"id\": 31012,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE ARMAS\",\n" +
"		\"id\": 30207,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE BEBIDAS\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE COHETES FUEGOS ARTIFICIALES\",\n" +
"		\"id\": 30808,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,ProteccionCivil\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE COMBUSTIBLE\",\n" +
"		\"id\": 30809,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE DROGAS\",\n" +
"		\"id\": 31014,\n" +
"		\"Dependencias\": \"DSPM,PoliciaFederal,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal,SEDENA\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE PRODUCTOS\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VENTA ILEGAL DE PRODUCTOS ALIMENTICIOS\",\n" +
"		\"id\": 31015,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"URGENTE\",\n" +
"		\"Incidente\": \"VIOLACION\",\n" +
"		\"id\": 30707,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VIOLENCIA CONTRA LA MUJER\",\n" +
"		\"id\": 30903,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VIOLENCIA DE PAREJA\",\n" +
"		\"id\": 30502,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	},\n" +
"	{\n" +
"		\"Prioridad\": \"RAPIDA\",\n" +
"		\"Incidente\": \"VIOLENCIA FAMILIAR\",\n" +
"		\"id\": 30503,\n" +
"		\"Dependencias\": \"DSPM,PoliciaEstatal,PoliciaMinisterial,PoliciaMunicipal\"\n" +
"	}\n" +
"]";
        JSONParser parser = new JSONParser();
        JSONArray jsonObj = (JSONArray) parser.parse(json);
        System.out.println(jsonObj);
    }

}
