/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author moises
 */
public class SAMIH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n\"name\":\"ANGEL\",\r\n\"surname\":\"LOPEZ\",\r\n\"surname1\":null,\r\n\"sex\":\"M\",\r\n\"birthDay\":\"02/01/1990\",\r\n\"nationality\":\"MEX\",\r\n\"birthCountry\":\"MEX\",\r\n\"birthState\":\"09\",\r\n\"nameKeeper\":\"PABLO\",\r\n\"surnameKeeper\":\"LOPEZ\",\r\n\"surname1Keeper\":\"MENDOZA\",\r\n\"sexKeeper\":\"M\",\r\n\"relationKeeper\":\"SIB\",\r\n\"telephoneKeeper\":\"5510223344\",\r\n\"emailKeeper\":\"correo@gmail.com\",\r\n\"center\":\"DFSSA003162\",\r\n\"nameProfessional\":\"EHCS\",\r\n\"telephoneProfessional\":\"4556433443\"\r\n}");
        Request request = new Request.Builder()
                .url("https://189.240.124.114:2020/ehHIS-ui/restApi/SAMIH/patientAdmission")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "ehCOSBROWSERID=0b9463e802784ac9b0a9caf3c047c0b3; JSESSIONID=jFrl-9W3g-1fJM5J58il8RjYJsIkhqFHgUeasrXI_DUnS8_GiXfp!2029977707")
                .build();
        Response response = client.newCall(request).execute();
    }

}
