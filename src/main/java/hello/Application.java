package hello;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class Application {

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://apisandbox-api.zuora.com/rest/connections";

        final APIInfo apiInfo = new APIInfo();
        apiInfo.setApiAccessKeyId("fnc@openenglish.com");
        apiInfo.setApiSecretAccessKey("Finance000");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
 //       map.add("Authorization","Basic Zm5jQG9wZW5lbmdsaXNoLmNvbTpGaW5hbmNlMDAw");
        map.add("apiAccessKeyId",apiInfo.getApiAccessKeyId());
        map.add("apiSecretAccessKey", apiInfo.getApiSecretAccessKey());

//
       // }

        HttpHeaders headers = new HttpHeaders(){
            {
                String auth = apiInfo.getApiAccessKeyId() + ":" + apiInfo.getApiSecretAccessKey();
                byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")),Base64.BASE64DEFAULTLENGTH).getBytes();
                String authHeader = "Basic " + new String( encodedAuth );
                set( "Authorization", authHeader );
            }
        };

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//        messageConverters.add(new MappingJacksonHttpMessageConverter());
//        messageConverters.add(new FormHttpMessageConverter());
//        restTemplate.setMessageConverters(messageConverters);

        String response = (String) restTemplate.postForObject(url, request, String.class);
        System.out.println("Response " + response);

        String createPaymentMethodURL = "https://apisandbox-api.zuora.com/rest/v1/payment-methods/credit-cards";
        map = new LinkedMultiValueMap<String, Object>();

        map.add("accountKey", "OE00011280");
        map.add("creditCardType","MasterCard");
        map.add("creditCardNumber","5555555555554444");
        map.add("expirationMonth",12);
        map.add("expirationYear", 2020);
        map.add("securityCode",null);
        map.add("defaultPaymentMethod", false);

        MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();


        map1.add("cardHolderName", "j.c");
        map1.add("addressLine1","3400 Bridge Pkwy");
        map1.add("addressLine2","#000");
           map1.add("city", null);
            map1.add("state", null);
            map1.add("zipCode", "94000");
            map1.add("country","United States");
            map1.add("phone","+1(123)4567890");
            map1.add("email", "j.c@abc.com");
            //        }

        map.add("cardHolderInfo",map1);
        HttpEntity<MultiValueMap<String, Object>> request1 = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        String response1 = (String) restTemplate.postForObject(createPaymentMethodURL, request1, String.class);
        System.out.println("PM Response " + response1);
    }

}
