//package com.e_commerce.order_api.header;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//@Service
//public class HeaderGenerator {
//    public HttpHeaders getHeadersForSuccessGetMethod(){
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type","application/json; charset=UTF-8");
//        return httpHeaders;
//    }
//    public HttpHeaders getHeadersForSuccessPostMethod(HttpServletRequest request, Long newResource){
//        HttpHeaders httpHeaders = new HttpHeaders();
//        try {
//            httpHeaders.setLocation(new URI(request.getRequestURI() + "/" + newResource));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        httpHeaders.add("Content-Type","application/json; charset=UTF-8");
//        return httpHeaders;
//    }
//    public HttpHeaders getHeadersForError(){
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type","application/problem+json; charset=UTF-8");
//        return httpHeaders;
//    }
//}
package com.e_commerce.order_api.header;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class HeaderGenerator {
    public HttpHeaders getHeadersForSuccessGetMethod(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json; charset=UTF-8");
        return httpHeaders;
    }

    public HttpHeaders getHeadersForSuccessPostMethod(HttpServletRequest request, Long newResource){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (request != null && newResource != null) {
            try {
                String uriString = request.getRequestURI();
                if (!uriString.endsWith("/")) {
                    uriString += "/";
                }
                uriString += newResource.toString();
                httpHeaders.setLocation(new URI(uriString));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        httpHeaders.add("Content-Type","application/json; charset=UTF-8");
        return httpHeaders;
    }

    public HttpHeaders getHeadersForError(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/problem+json; charset=UTF-8");
        return httpHeaders;
    }
}
